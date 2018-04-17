package com.wonder.mongodb.api.foundation;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OperatorProperties {

	public static final String MONGODB_OPTION_UNIQUE = "unique";
	public static final DBObject MONGODB_OPTION_UNIQUE_DBO = new BasicDBObject("unique", true);
	public static final String MONGODB_OPTION_EXISTS = "$exists";
	public static final DBObject MONGODB_OPTION_EXISTS_DBO = new BasicDBObject("$exists", true);
	
}
