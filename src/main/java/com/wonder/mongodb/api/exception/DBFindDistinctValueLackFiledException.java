package com.wonder.mongodb.api.exception;

public class DBFindDistinctValueLackFiledException extends Exception {

	private static final long serialVersionUID = 5605616161987833383L;
	private static final String info = "在使用缓存去重查找数据时，没有指定Filed";
	
	public DBFindDistinctValueLackFiledException() {
		super(info);
	}

}
