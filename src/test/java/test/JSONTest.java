package test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class JSONTest {

	public static void main(String[] args) {
		BasicDBObject dbo = new BasicDBObject();
		dbo.append("id", "689984562");
		BasicDBList list = new BasicDBList();
		BasicDBList innerList = new BasicDBList();
		innerList.add(new BasicDBObject("0", new BasicDBObject("labelValue", "2016").append("score", 30.0)));
		innerList.add(new BasicDBObject("1", new BasicDBObject("labelValue", "言情").append("score", 30.0)));
		innerList.add(new BasicDBObject("2", new BasicDBObject("labelValue", "喜剧").append("score", 40.0)));
		list.add(new BasicDBObject("0", new BasicDBObject().append("catName", "电视剧").append("score", 23.5)).append("items", innerList));
		innerList = new BasicDBList();
		innerList.add(new BasicDBObject("0", new BasicDBObject("labelValue", "2014").append("score", 30.0)));
		innerList.add(new BasicDBObject("1", new BasicDBObject("labelValue", "2017").append("score", 30.0)));
		innerList.add(new BasicDBObject("2", new BasicDBObject("labelValue", "喜剧").append("score", 40.0)));
		list.add(new BasicDBObject("1", new BasicDBObject().append("catName", "电影").append("score", 76.5)).append("items", innerList));
		dbo.append("cats", list);
		System.out.println(dbo);
	}

}
