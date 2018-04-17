package com.wonder.mongodb.api;

import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.foundation.MongoDBConfig;

public class MongoDBConfigWithNoAuth implements MongoDBConfig {

	private String des;
	private int port;
	private String[] toBeIndexedFields;
	private String[] toBeUniqueFields;
	
	public MongoDBConfigWithNoAuth(String des, int port, String[] toBeIndexedFields, String[] toBeUniqueFields) throws NoServerIPException, IllegalPortValueException {
		if(des == null) {
			throw new NoServerIPException();
		}
		if(port >= 65536 || port <= 0) {
			throw new IllegalPortValueException();
		}
		this.des = des;
		this.port = port;
		this.toBeIndexedFields = toBeIndexedFields;
		this.toBeUniqueFields = toBeUniqueFields;
	}
	
	public String getIP() {
		return des;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return null;
	}

	public String getPassword() {
		return null;
	}

	public String[] getIndexesFields() {
		return toBeIndexedFields;
	}

	public String[] getUniqueFields() {
		return toBeUniqueFields;
	}

}
