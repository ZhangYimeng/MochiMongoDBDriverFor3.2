package com.wonder.mongodb.api;

import java.io.Serializable;

import com.wonder.mongodb.api.exception.IllegalPortValueException;
import com.wonder.mongodb.api.exception.NoServerIPException;
import com.wonder.mongodb.api.foundation.MongoDBConfig;

/**
 * It doesn't has a lot fields, because it is just a cache tool.
 * @author zhangyimeng
 *
 */
public class MongoDBConfigWithAuth implements MongoDBConfig,Serializable {

	private static final long serialVersionUID = 7163099660036551572L;
	private String des;
	private int port;
	private String username;
	private String password;
	private String[] toBeIndexedFields;
	private String[] toBeUniqueFields;
	
	public MongoDBConfigWithAuth(String des, int port, String username, String password, String[] toBeIndexedFields, String[] toBeUniqueFields) throws NoServerIPException, IllegalPortValueException {
		if(des == null) {
			throw new NoServerIPException();
		}
		if(port >= 65536 || port <= 0) {
			throw new IllegalPortValueException();
		}
		this.des = des;
		this.port = port;
		this.username = username;
		this.password = password;
		this.toBeIndexedFields = toBeIndexedFields;
		this.toBeUniqueFields = toBeUniqueFields;
	}

	public String getIP() {
		return des;
	}

	public int getPort() {
		return port;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String[] getIndexesFields() {
		return toBeIndexedFields;
	}

	public String[] getUniqueFields() {
		return toBeUniqueFields;
	}
	
	public String toString() {
		return "Server:" + des + " " + "Port:" + port + " " + "Username:" + username + " " + "Password:" + password;
	}
	
}
