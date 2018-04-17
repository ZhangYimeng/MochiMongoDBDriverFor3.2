package com.wonder.mongodb.api.foundation;

public interface MongoDBConfig {

	public String getIP();
	
	public int getPort();
	
	public String getUsername();
	
	public String getPassword();
	
	public String[] getIndexesFields();
	
	public String[] getUniqueFields();
	
}
