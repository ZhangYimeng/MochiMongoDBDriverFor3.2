package com.wonder.mongodb.api.foundation;

public class QueryOperators {

	/**
	 * 用来查找已经存在的键所对应的所有键值对。
	 */
	public static final String $EXIST = "$exists";
	
	/**
	 * 匹配键值不等于指定值的文档。
	 */
	public static final String $NE = "$ne";
	
	/**
	 * 匹配键不存在或者键值不等于指定数组的任意值的文档。
	 */
	public static final String $NIN = "$nin";
	
	/**
	 * 匹配键值大于指定值的所有文档。
	 */
	public static final String $GT = "$gt";
	
	/**
	 * 匹配键值大于等于指定值得所有文档。
	 */
	public static final String $GTE = "$gte";
	
	/**
	 * 匹配键值小于指定值的所有文档。
	 */
	public static final String $LT = "$lt";
	
	/**
	 * 匹配键值小于等于指定值的所有文档。
	 */
	public static final String $LTE = "$lte";

	/**
	 * 使用Regex实现模糊搜索。
	 */
	public static final String $REGEX = "$regex";

}
