package com.wonder.mongodb.api.exception;

public class CollectionNotExistException extends Exception {

	private static final long serialVersionUID = -3575867131077843197L;
	private static final String info = "集合不存在，切换失败。";
	
	public CollectionNotExistException() {
		super(info);
	}

}
