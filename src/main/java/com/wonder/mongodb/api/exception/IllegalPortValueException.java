package com.wonder.mongodb.api.exception;

public class IllegalPortValueException extends Exception {

	private static final long serialVersionUID = -4159330242836965379L;
	private static final String info = "端口号数据异常。";

	public IllegalPortValueException() {
		super(info);
	}
	
}
