package test;

import com.wonder.mongodb.api.DistinctResults;
import com.wonder.mongodb.api.Duality;
import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.exception.CollectionNotExistException;
import com.wonder.mongodb.api.exception.DBFindDistinctValueLackFiledException;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;

public class DataTest {

	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, SelectedCollectionWithNoIndexesException, CollectionNotExistException, DBFindDistinctValueLackFiledException {
		MongoDBConfigWithAuth conf = new MongoDBConfigWithAuth("192.168.1.37", 27017, "mochi", "gotohellmyevilex", null, null);
		MongoDBPlayer player = new MongoDBPlayer(conf, "BYApp", "UserRegisterTotalCountForAppid");
		Duality filter = new Duality();
		filter.append("baiyingno", "Xul");
		DistinctResults<String> results = player.advancedDistinct("appid", filter, String.class);
		while(results.hasNext()) {
			System.out.println(results.next().getValue());
		}
//		Random random = new Random();
//		for(int i = 0; i < 200; i++) {
//			player.switchCollection("DataSourceID");
//			Duality filter = new Duality();
//			Duality value = new Duality();
//			filter.append("serialid", random.nextInt(120000));
//			Result result = player.getData(filter).next();
//			value.append("gatewayid", result.get("gatewayid"));
//			value.append("datasourceid", result.get("datasourceid"));
//			value.append("type", result.get("type"));
//			if(result.get("type").equals("at")) {
//				value.append("value", 23.4);
//			} else if(result.get("type").equals("ah")) {
//				float base = (float) 2.232;
//				value.append("value", base + random.nextFloat() / 1000);
//			} else if(result.get("type").equals("sm")) {
//				float base = (float) 67.784;
//				value.append("value", base + random.nextFloat() / 1000);
//			} else if(result.get("type").equals("st")) {
//				continue;
//			} else if(result.get("type").equals("sten")) {
//				continue;
//			} else if(result.get("type").equals("sph")) {
//				continue;
//			} else if(result.get("type").equals("lux")) {
//				continue;
//			} else if(result.get("type").equals("rfc")) {
//				continue;
//			} else if(result.get("type").equals("windd")) {
//				continue;
//			} else if(result.get("type").equals("winds")) {
//				continue;
//			} else if(result.get("type").equals("co2")) {
//				continue;
//			} else if(result.get("type").equals("wss")) {
//				continue;
//			} else if(result.get("type").equals("sn")) {
//				continue;
//			}
//			value.append("timestamp", System.currentTimeMillis());
//			player.switchCollection("Section_E45TYG89");
//			player.insertData(value);
//		}
//		Duality dual = new Duality();
//		byte[] uuu = {0, 0, 0, 0};
//		dual.append("dada", uuu);
////		player.insertData(dual);
//		Results results = player.getData(dual);
//		Result result = results.next();
//		System.out.println(result.get("dada"));
//		Binary uuuu = (Binary) result.get("dada");
//		for(byte b: uuuu.getData()) {
//			System.out.print(b);
//		}
	}

}
