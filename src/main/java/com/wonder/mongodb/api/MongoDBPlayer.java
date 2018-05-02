package com.wonder.mongodb.api;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexOptions;
import com.wonder.mongodb.api.exception.CollectionNotExistException;
import com.wonder.mongodb.api.exception.DBFindDistinctValueLackFiledException;
import com.wonder.mongodb.api.exception.FieldToBeSUMIsNotIntegerException;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;
import com.wonder.mongodb.api.foundation.ManualControlExcutor;
import com.wonder.mongodb.api.foundation.MongoDBConfig;
import com.wonder.mongodb.api.foundation.interfaces.MongoManualFunction;

public class MongoDBPlayer implements Serializable {

	private static final long serialVersionUID = -4674059483998226749L;
	private MongoClient mc;
	private MongoDatabase currentDB;
	private DB legacyDB;
	private MongoCollection<Document> currentCollection;
	private AtomicBoolean manualControlFlag;
	private int[] lock;

	@SuppressWarnings("deprecation")
	public MongoDBPlayer(MongoDBConfig conf, String currentDataBaseName, String currentCollectionName)
			throws SelectedCollectionWithNoIndexesException {
		if (conf.getClass().equals(MongoDBConfigWithNoAuth.class)) {
			mc = new MongoClient(conf.getIP(), conf.getPort());
		} else if (conf.getClass().equals(MongoDBConfigWithAuth.class)) {
			ServerAddress server = new ServerAddress(conf.getIP(), conf.getPort());
			MongoCredential cred = MongoCredential.createCredential(conf.getUsername(), "admin",
					conf.getPassword().toCharArray());
			mc = new MongoClient(server, Arrays.asList(cred));
		}
		manualControlFlag = new AtomicBoolean();
		manualControlFlag.set(false);
		currentDB = mc.getDatabase(currentDataBaseName);
		legacyDB = mc.getDB(currentDataBaseName);
		currentCollection = currentDB.getCollection(currentCollectionName);
		Iterator<Document> indexsIt = currentCollection.listIndexes().iterator();
		if (!indexsIt.hasNext()) {
			String[] toBeIndexedFields = conf.getIndexesFields();
			if (toBeIndexedFields != null) {
				for (String field : toBeIndexedFields) {
					currentCollection.createIndex(new Document(field, 1));
				}
			} else {
				throw new SelectedCollectionWithNoIndexesException();
			}
			String[] toBeUniqueFields = conf.getUniqueFields();
			if (toBeUniqueFields != null) {
				for (String field : toBeUniqueFields) {
					currentCollection.createIndex(new Document(field, 2), new IndexOptions().unique(true).sparse(true));
				}
			}
		}
		lock = new int[0];
	}

	@SuppressWarnings("deprecation")
	public void switchDB(String dataBaseName) {
		currentDB = mc.getDatabase(dataBaseName);
		legacyDB = mc.getDB(dataBaseName);
	}

	public void switchCollection(String collectionName) throws CollectionNotExistException {
		if (legacyDB.collectionExists(collectionName)) {
			currentCollection = currentDB.getCollection(collectionName);
		} else {
			throw new CollectionNotExistException();
		}
	}

	public Results getData(Duality filter) {
		FindIterable<Document> fit = null;
		long size = 0;
		if (filter != null) {
			fit = currentCollection.find(filter.getInsideDoc());
			size = currentCollection.count(filter.getInsideDoc());
		} else {
			fit = currentCollection.find();
			size = currentCollection.count();
		}
		return new Results(fit, fit.iterator(), size);
	}
	
	public Results getData(Duality filter, boolean noCursorTimeout) {
		FindIterable<Document> fit = null;
		long size = 0;
		if (filter != null) {
			fit = currentCollection.find(filter.getInsideDoc()).noCursorTimeout(noCursorTimeout);
			size = currentCollection.count(filter.getInsideDoc());
		} else {
			fit = currentCollection.find().noCursorTimeout(noCursorTimeout);
			size = currentCollection.count();
		}
		return new Results(fit, fit.iterator(), size);
	}
	
	public Results getData(Duality filter, int limit) {
		FindIterable<Document> fit = null;
		long size = 0;
		if (filter != null) {
			fit = currentCollection.find(filter.getInsideDoc()).limit(limit);
			size = currentCollection.count(filter.getInsideDoc());
			size = Math.min(size, limit);
		} else {
			fit = currentCollection.find().limit(limit);
			size = currentCollection.count();
			size = Math.min(size, limit);
		}
		return new Results(fit, fit.iterator(), size);
	}
	
	public Results getData(Duality filter, int skip, int limit) {
		FindIterable<Document> fit = null;
		long size = 0;
		if (filter != null) {
			fit = currentCollection.find(filter.getInsideDoc()).skip(skip).limit(limit);
			size = currentCollection.count(filter.getInsideDoc());
			size = Math.min(size, limit);
		} else {
			fit = currentCollection.find().skip(skip).limit(limit);
			size = currentCollection.count();
			size = Math.min(size, limit);
		}
		return new Results(fit, fit.iterator(), size);
	}

	public void insertData(Duality duality) {
		currentCollection.insertOne(duality.getInsideDoc());
	}

	/**
	 * 找到一个符合条件的数据，进行更新，如果没找到，则插入待更新的数据。
	 * 
	 * @param filter
	 * @param update
	 */
	public void findAndAlwaysUpdate(Duality filter, Duality update) {
		FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
		options.upsert(true);
		currentCollection.findOneAndUpdate(filter, update, options);
	}

	/**
	 * 找到一个符合条件的数据，进行更新，如果没找到，则什么都不做。
	 * 
	 * @param filter
	 * @param update
	 */
	public void findAndUpdate(Duality filter, Duality update) {
		currentCollection.findOneAndUpdate(filter, update);
	}

	public Results getRankedList(Duality filter, String field, int order, int number) {
		FindIterable<Document> fit = currentCollection.find(filter).sort(new Document(field, order)).limit(number);
		long size = 0;
		size = currentCollection.count(filter);
		return new Results(fit, fit.iterator(), size);
	}

	public long getCount(Duality filter) {
		return currentCollection.count(filter.getInsideDoc());
	}

	public <T> DistinctResults<T> advancedDistinct(String field, Duality filter, Class<T> className)
			throws DBFindDistinctValueLackFiledException {
		if (field != null) {
			DistinctIterable<T> distinct = currentCollection.distinct(field, filter, className);
			return new DistinctResults<T>(distinct, distinct.iterator());
		} else {
			throw new DBFindDistinctValueLackFiledException();
		}
	}

	public void createIndex(Duality duality) {
		currentCollection.createIndex(duality.getInsideDoc());
	}

	public long count() {
		return currentCollection.count();
	}

	public void deleteData(Duality filter) {
		if (filter != null) {
			currentCollection.deleteMany(filter);
		} else {
			Duality ugly = new Duality();
			currentCollection.deleteMany(ugly);
		}
	}

	public Results deleteDataSyn(Duality filter) {
		synchronized (lock) {
			Results results = getData(filter);
			if (filter != null) {
				currentCollection.deleteMany(filter);
			} else {
				Duality ugly = new Duality();
				currentCollection.deleteMany(ugly);
			}
			return results;
		}
	}

	public void close() {
		this.mc.close();
	}

	/**
	 * 按照自己的意愿对缓存进行周期性操作，例如，为了增加效率，使用了线程不安全的操作而导致的数据重复，可以在这里进行手动整合；
	 * 又如可以在这里对缓存进行定期刷新等等的操作。
	 * 
	 * @param function
	 *            周期性操作缓存的方法。
	 * @param delay
	 *            第一次执行的延迟时间。
	 * @param interval
	 *            每次执行的时间间隔。
	 */
	public synchronized void setManualControl(MongoManualFunction function, long delay, long interval) {
		if (manualControlFlag.get() == false) {
			ManualControlExcutor mce = new ManualControlExcutor(function, this);
			mce.schedule(delay, interval);
			manualControlFlag.set(true);
		}
	}

	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException,
			FieldToBeSUMIsNotIntegerException, SelectedCollectionWithNoIndexesException {
//		ServerAddress server = new ServerAddress("127.0.0.1", 27017);
//		MongoCredential cred = MongoCredential.createCredential("mochi", "admin", "gotohellmyevilex".toCharArray());
//		MongoClient mc = new MongoClient(server, Arrays.asList(cred));
//		MongoDatabase db = mc.getDatabase("test");
//		MongoCollection<Document> collection = db.getCollection("test");
//		collection.insertOne(new Document("date", "1").append("name", "mochi"));
//		collection.insertOne(new Document("date", "1").append("name", "zhangyimeng"));
//		collection.insertOne(new Document("date", "1").append("name", "yizhuo"));
//		collection.insertOne(new Document("date", "1").append("name", "yihan"));
//		collection.insertOne(new Document("date", "1").append("name", "lanyangyang"));
//		collection.insertOne(new Document("date", "2").append("name", "mochi"));
//		collection.insertOne(new Document("date", "2").append("name", "zhangyimeng"));
//		collection.insertOne(new Document("date", "2").append("name", "yizhuo"));
//		collection.insertOne(new Document("date", "2").append("name", "yihan"));
//		ArrayList<Document> list = new ArrayList<Document>();
//		Document filter1 = new Document();
//		filter1.append("$match", new Document("date", "1"));
//		Document filter2 = new Document();
//		filter2.append("$match", new Document("name", "yizhuo").append("date", "2"));
//		list.add(filter1);
//		list.add(filter2);
//		Iterator<Document> it = collection.aggregate(list).iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
//		mc.close();
		MongoDBConfigWithAuth conf = new MongoDBConfigWithAuth("127.0.0.1", 27017, "mochi", "gotohellmyevilex", null, null);
		MongoDBPlayer db = new MongoDBPlayer(conf, "TestData", "TestCollection");
		Duality data = new Duality();
		data.append("aaa", 1);
		db.insertData(data);
		Duality filter = new Duality();
//		filter.append("aaa", 1);
		System.out.println(db.getCount(filter));
	}

}
