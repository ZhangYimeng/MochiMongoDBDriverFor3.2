package com.wonder.mongodb.api.exception;

public class FieldToBeSUMIsNotIntegerException extends Exception {

	private static final long serialVersionUID = 1664046723677301353L;
	private static final String info = "求和的字段类型不是整数。";
	
	public FieldToBeSUMIsNotIntegerException() {
		super(info);
	}

}
