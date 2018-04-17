package test;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wonder.mongodb.api.Duality;
import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;
import com.wonder.mongodb.api.foundation.MongoDBConfig;
import com.wonder.mongodb.api.foundation.UpdateOperators;

public class UniqueTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, SelectedCollectionWithNoIndexesException {
		String[] indexFields = {"a", "b", "c", "d"};
		String[] uniqueFields = {"abcd"};
		MongoDBConfig conf = new MongoDBConfigWithAuth("192.168.1.37", 27017, "mochi", "gotohellmyevilex", indexFields, uniqueFields);
		MongoDBPlayer player = new MongoDBPlayer(conf, "test", "DataInsertTest");
		
		ServerAddress server = new ServerAddress("192.168.1.37", 27017);
		MongoCredential cred = MongoCredential.createCredential("mochi", "admin", "gotohellmyevilex".toCharArray());
		@SuppressWarnings("resource")
		MongoClient mc = new MongoClient(server, Arrays.asList(cred));
		MongoDatabase db = mc.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("DataInsertTest");
		//"a" : "fb623001-9307-4561-9b1a-9023bb9c8270"
		Duality filter = new Duality();
		filter.append("a", "fb623001-9307-4561-9b1a-9023bb9c8270");
		Duality value = new Duality();
		value.append("b", "1234567");
		Duality update = new Duality();
		update.append(UpdateOperators.$SET, value);
		player.findAndAlwaysUpdate(filter, update);
	}

}
