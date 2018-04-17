package com.wonder.mongodb.api;

import java.io.Serializable;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.wonder.mongodb.api.exception.FieldToBeSUMIsNotIntegerException;

public class Results implements Serializable {

	private static final long serialVersionUID = -3324538925529040700L;
	private MongoCursor<Document> resultsc;
	private FindIterable<Document> resultsf;
	private MongoCursor<Document> temp;
	private long size;
	
	public Results(FindIterable<Document> resultsf, MongoCursor<Document> resultsc, long size) {
		this.resultsc = resultsc;
		this.resultsf = resultsf;
		this.size = size;
	}
	
	public boolean hasNext() {
		return resultsc.hasNext();
	}
	
	public Result next() {
		return new Result(resultsc.next());
	}
	
	/**
	 * 按字段排序,操作后不可逆。
	 * @param field 字段。
	 * @param order 正数，升序；负数，降序。
	 */
	public void sort(String field, int order) {
		resultsf = resultsf.sort(new Document(field, order));
		resultsc = resultsf.iterator();
	}
	
	/**
	 * 将整数类型的字段求和，效率较低。
	 * @param field 字段。
	 * @throws FieldToBeSUMIsNotIntegerException 
	 */
	public long sum(String field) throws FieldToBeSUMIsNotIntegerException {
		try {
			temp = resultsf.iterator();
			long sum = 0;
			while(temp.hasNext()) {
				Document doc = temp.next();
				sum += Long.parseLong(doc.get(field).toString());
			}
			return sum;
		} catch(Exception e) {
			throw new FieldToBeSUMIsNotIntegerException();
		}
	}
	
	/**
	 * 将整数类型的字段求和。
	 * @param field 字段。
	 * @throws FieldToBeSUMIsNotIntegerException 
	 */
	public long sumLong(String field) throws FieldToBeSUMIsNotIntegerException {
		try {
			temp = resultsf.iterator();
			long sum = 0;
			while(temp.hasNext()) {
				Document doc = temp.next();
				sum += (Long) doc.get(field);
			}
			return sum;
		} catch(Exception e) {
			throw new FieldToBeSUMIsNotIntegerException();
		}
	}
	
	/**
	 * 将整数类型的字段求和。
	 * @param field 字段。
	 * @throws FieldToBeSUMIsNotIntegerException 
	 */
	public long sumInteger(String field) throws FieldToBeSUMIsNotIntegerException {
		try {
			temp = resultsf.iterator();
			long sum = 0;
			while(temp.hasNext()) {
				Document doc = temp.next();
				sum += (Integer) doc.get(field);
			}
			return sum;
		} catch(Exception e) {
			throw new FieldToBeSUMIsNotIntegerException();
		}
	}
	
	/**
	 * 将整数类型的字段求和。
	 * @param field 字段。
	 * @throws FieldToBeSUMIsNotIntegerException 
	 */
	public long sumShort(String field) throws FieldToBeSUMIsNotIntegerException {
		try {
			temp = resultsf.iterator();
			long sum = 0;
			while(temp.hasNext()) {
				Document doc = temp.next();
				sum += (Short) doc.get(field);
			}
			return sum;
		} catch(Exception e) {
			throw new FieldToBeSUMIsNotIntegerException();
		}
	}
	
	public long size() {
		return size;
	}
	
}
