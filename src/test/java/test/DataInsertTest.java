package test;

import com.wonder.mongodb.api.Duality;
import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.Result;
import com.wonder.mongodb.api.Results;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;
import com.wonder.mongodb.api.foundation.MongoDBConfig;
import com.wonder.mongodb.api.foundation.QueryOperators;

public class DataInsertTest {

	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, SelectedCollectionWithNoIndexesException {
		String[] indexFields = {"a", "b", "c", "d"};
		String[] uniqueFields = {"abcd"};
		MongoDBConfig conf = new MongoDBConfigWithAuth("192.168.1.37", 27017, "mochi", "gotohellmyevilex", indexFields, uniqueFields);
		MongoDBPlayer player = new MongoDBPlayer(conf, "test", "DataInsertTest");
//		while(true) {
//			Duality dual = new Duality();
//			dual.append("a", UUID.randomUUID().toString());
//			dual.append("b", UUID.randomUUID().toString());
//			dual.append("c", UUID.randomUUID().toString());
//			dual.append("d", UUID.randomUUID().toString());
//			dual.append("abcd", UUID.randomUUID().toString());
//			player.insertData(dual);
//		}
		Duality filter = new Duality();
		filter.append("a", new Duality(QueryOperators.$GTE, "15"));
		Results results = player.getData(filter);
		while(results.hasNext()) {
			Result result = results.next();
			System.out.println(result);
		}
		System.out.println();
	}

}
