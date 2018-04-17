package com.wonder.mongodb.api.exception;

public class SelectedCollectionWithNoIndexesException extends Exception {

	private static final long serialVersionUID = -8467305608867666800L;
	private static final String info = "选择的数据库集合缺少索引。";
	
	public SelectedCollectionWithNoIndexesException() {
		super(info);
	}

}
