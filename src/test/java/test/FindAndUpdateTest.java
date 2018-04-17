package test;

import java.util.Random;
import java.util.UUID;

import com.wonder.mongodb.api.Duality;
import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.Result;
import com.wonder.mongodb.api.Results;
import com.wonder.mongodb.api.exception.CollectionNotExistException;
import com.wonder.mongodb.api.exception.DBFindDistinctValueLackFiledException;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;

public class FindAndUpdateTest {

	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, 
	SelectedCollectionWithNoIndexesException, DBFindDistinctValueLackFiledException, CollectionNotExistException {
		String[] indexFields = {"gatewayid", "datasourceid", "type", "serialid"};
		MongoDBConfigWithAuth conf = new MongoDBConfigWithAuth("127.0.0.1", 27017, "mochi", "gotohellmyevilex", indexFields, null);
		MongoDBPlayer player = new MongoDBPlayer(conf, "NothingButNothing", "DataSourceID");
		Duality value = new Duality();
		Duality filter = new Duality();
		String[] type = {"at", "ah", "sm", "st", "sten", "sph", "lux", "rfc", "windd", "winds", "co2", "wss", "sn"};
//		value.append("gatewayid", "EDF76OP894").append("sensorid", "IO8756UJH12").append("temperature", "24.4").append("timestamp", "201507040555");
		Random random = new Random();
		for(int i = 0; i < 123849; i++) {
			value = new Duality();
			filter = new Duality();
			filter.append("serialid", random.nextInt(2000));
			player.switchCollection("GatewayID");
			Results results = player.getData(filter);
			if(results.hasNext()) {
				Result result = results.next();
				value.append("gatewayid", result.get("gatewayid"));
				value.append("datasourceid", UUID.randomUUID().toString().substring(0, 8));
				value.append("type", type[random.nextInt(type.length)]);
				value.append("serialid", i);
				player.switchCollection("DataSourceID");
				player.insertData(value);
			}
		}
		
	}

}
