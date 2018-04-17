package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertSpeedTest {

	public static void main(String[] args) throws IOException, NotAFileException {
		ServerAddress server = new ServerAddress("127.0.0.1", 27017);
		MongoCredential cred = MongoCredential.createCredential("mochi", "admin", "gotohellmyevilex".toCharArray());
		MongoClient mc = new MongoClient(server, Arrays.asList(cred));
		MongoDatabase db = mc.getDatabase("PaperTestDB");
		MongoCollection<Document> collection = db.getCollection("PaperTest");
		collection.createIndex(new Document("id", 1));
		MochiTxtFile mf = new MochiTxtFile("/Users/Zhangyimeng/TestDB.txt", true);
		List<Document> list = new ArrayList<Document>();
		while(mf.ready()) {
			String[] line = mf.readLine().split(",");
			Document doc = new Document();
			doc.append("id", Integer.parseInt(line[0]));
			doc.append("id", line[1]);
			list.add(doc);
			if(list.size() == 1000000) {
				long t1 = System.currentTimeMillis();
				collection.insertMany(list);
				System.out.println(System.currentTimeMillis() - t1);
				list = new ArrayList<Document>();
			}
		}
		mc.close();
	}

}
