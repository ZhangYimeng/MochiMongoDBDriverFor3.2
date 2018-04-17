package com.wonder.mongodb.api.exception;

public class NoServerIPException extends Exception {

	private static final long serialVersionUID = 5777199469428555608L;
	private static final String info = "没有指定数据库IP。";
	
	public NoServerIPException() {
		super(info);
	}

}
