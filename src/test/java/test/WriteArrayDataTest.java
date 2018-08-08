package test;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bson.Document;

import com.wonder.mongodb.api.MongoDBConfigWithAuth;
import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.Result;
import com.wonder.mongodb.api.Results;
import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.exception.SelectedCollectionWithNoIndexesException;

public class WriteArrayDataTest {

	public static void main(String[] args) throws NoServerIPException, IllegalPortValueException, SelectedCollectionWithNoIndexesException {
		MongoDBConfigWithAuth confTrainedWordVectorMapPlayer = new MongoDBConfigWithAuth("127.0.0.1", 27017, "mochi",
                "gotohellmyevilex", new String[]{"word"},
                new String[]{"word"});
		MongoDBPlayer trainedWordVectorMapPlayer = new MongoDBPlayer(confTrainedWordVectorMapPlayer, "RimMindTrainDatabase", "TrainedWordVectorMap");
		Results results = trainedWordVectorMapPlayer.getData(null);
		if(results.hasNext()) {
			Result result = results.next();
			Iterator<Entry<String, Object>> it = result.convertToDuality().entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, Object> entry = it.next();
				System.out.println(entry.getKey());
				System.out.println((List<Document>)entry.getValue());
			}
		}
	}

}
