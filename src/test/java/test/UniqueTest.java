package test;

import org.bson.types.ObjectId;

import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;
import com.wonder.mongodb.api.foundation.MongoDBConfig;

public class UniqueTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, SelectedCollectionWithNoIndexesException {
		String[] indexFields = {"a", "b", "c", "d"};
		String[] uniqueFields = {"abcd"};
		MongoDBConfig conf = new MongoDBConfigWithAuth("127.0.0.1", 27017, "mochi", "gotohellmyevilex", indexFields, uniqueFields);
		MongoDBPlayer player = new MongoDBPlayer(conf, "test", "DataInsertTest");
		ObjectId oid = new ObjectId("5b31b008803cab1ba80b33e2");
		System.out.println(oid.toString());
	}

}
