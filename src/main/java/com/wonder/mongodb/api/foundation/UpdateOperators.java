package com.wonder.mongodb.api.foundation;

public class UpdateOperators {
	
	/**
	 * "$set"用来指定一个键的值。如果这个键不存在，则创建它。
	 */
	public static final String $SET = "$set";
	
	/**
	 * Increments the value of the field by the specified amount.
	 */
	public static final String $INC = "$inc";

	/**
	 * Only updates the field if the specified value is less than the existing field value.
	 */
	public static final String $MIN = "$min";
	
}
