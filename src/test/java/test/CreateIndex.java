package test;

import com.wonder.mongodb.api.Duality;
import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.exception.CollectionNotExistException;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;

public class CreateIndex {

	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, SelectedCollectionWithNoIndexesException, CollectionNotExistException {
		MongoDBConfigWithAuth conf = new MongoDBConfigWithAuth("192.168.1.37", 27017, "mochi", "gotohellmyevilex", null, null);
		MongoDBPlayer player = new MongoDBPlayer(conf, "DB", "UserRegisterRecord");
		player.createIndex(new Duality("userid", 1));
		player.switchCollection("UserLoginRecord");
		player.createIndex(new Duality("userid", 1));
	}

}
