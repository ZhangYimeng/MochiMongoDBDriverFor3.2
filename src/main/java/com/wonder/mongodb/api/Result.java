package com.wonder.mongodb.api;

import java.io.Serializable;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Result implements Serializable {

	private static final long serialVersionUID = -1705256819036410554L;
	private Document doc;
	
	public Result(Document doc) {
		this.doc = doc;
	}
	
	public Result() {
		doc = new Document();
	}
	
	public void append(String key, Object value) {
		doc.append(key, value);
	}
	
	/**
     * Gets the value of the given key, casting it to the given {@code Class<T>}.  This is useful to avoid having casts in client code,
     * though the effect is the same.  So to get the value of a key that is of type String, you would write {@code String name =
     * doc.get("name", String.class)} instead of {@code String name = (String) doc.get("x") }.
     *
     * @param key   the key
     * @param clazz the non-null class to cast the value to
     * @param <T>   the type of the class
     * @return the value of the given key, or null if the instance does not contain this key.
     * @throws ClassCastException if the value of the given key is not of type T
     */
    public <T> T get(final Object key, final Class<T> clazz) {
        return doc.get(key, clazz);
    }
    
    /**
     * Gets the value of the given key as an Integer.
     *
     * @param key the key
     * @return the value as an integer, which may be null
     * @throws java.lang.ClassCastException if the value is not an integer
     */
    public Integer getInteger(final Object key) {
        return doc.getInteger(key);
    }

    /**
     * Gets the value of the given key as a primitive int.
     *
     * @param key          the key
     * @param defaultValue what to return if the value is null
     * @return the value as an integer, which may be null
     * @throws java.lang.ClassCastException if the value is not an integer
     */
    public int getInteger(final Object key, final int defaultValue) {
        return doc.getInteger(key, defaultValue);
    }

    /**
     * Gets the value of the given key as a Long.
     *
     * @param key the key
     * @return the value as a long, which may be null
     * @throws java.lang.ClassCastException if the value is not an long
     */
    public Long getLong(final Object key) {
        return doc.getLong(key);
    }

    /**
     * Gets the value of the given key as a Double.
     *
     * @param key the key
     * @return the value as a double, which may be null
     * @throws java.lang.ClassCastException if the value is not an double
     */
    public Double getDouble(final Object key) {
        return doc.getDouble(key);
    }

    /**
     * Gets the value of the given key as a String.
     *
     * @param key the key
     * @return the value as a String, which may be null
     * @throws java.lang.ClassCastException if the value is not a String
     */
    public String getString(final Object key) {
        return doc.getString(key);
    }

    /**
     * Gets the value of the given key as a Boolean.
     *
     * @param key the key
     * @return the value as a double, which may be null
     * @throws java.lang.ClassCastException if the value is not an double
     */
    public Boolean getBoolean(final Object key) {
        return doc.getBoolean(key);
    }

    /**
     * Gets the value of the given key as a primitive boolean.
     *
     * @param key          the key
     * @param defaultValue what to return if the value is null
     * @return the value as a double, which may be null
     * @throws java.lang.ClassCastException if the value is not an double
     */
    public boolean getBoolean(final Object key, final boolean defaultValue) {
        return doc.getBoolean(key, defaultValue);
    }

    /**
     * Gets the value of the given key as an ObjectId.
     *
     * @param key the key
     * @return the value as an ObjectId, which may be null
     * @throws java.lang.ClassCastException if the value is not an ObjectId
     */
    public ObjectId getObjectId(final Object key) {
        return doc.getObjectId(key);
    }

    /**
     * Gets the value of the given key as a Date.
     *
     * @param key the key
     * @return the value as a Date, which may be null
     * @throws java.lang.ClassCastException if the value is not a Date
     */
    public Date getDate(final Object key) {
        return doc.getDate(key);
    }
    
    public Object get(final Object key) {
        return doc.get(key);
    }
    
    public Duality convertToDuality() {
		return new Duality(doc);
    }
    
    public String toString() {
    	return doc.toJson();
    }
	
}
