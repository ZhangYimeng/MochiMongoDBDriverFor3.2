package com.wonder.mongodb.api;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.Encoder;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

public class Duality extends Document {

	private static final long serialVersionUID = 6297731997167536582L;
	private Document doc;

	public Document getInsideDoc() {
		return doc;
	}

	public Duality() {
		doc = new Document();
	}

	/**
	 * Create a Document instance initialized with the given key/value pair.
	 *
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public Duality(final String key, final Object value) {
		doc = new Document(key, value);
	}

	/**
	 * Creates a Document instance initialized with the given map.
	 *
	 * @param map
	 *            initial map
	 */
	public Duality(final Map<String, Object> map) {
		doc = new Document(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#toBsonDocument(java.lang.Class,
	 * org.bson.codecs.configuration.CodecRegistry)
	 */
	@Override
	public <C> BsonDocument toBsonDocument(Class<C> documentClass, CodecRegistry codecRegistry) {
		return doc.toBsonDocument(documentClass, codecRegistry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#append(java.lang.String, java.lang.Object)
	 */
	@Override
	public Document append(String key, Object value) {
		return doc.append(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#get(java.lang.Object, java.lang.Class)
	 */
	@Override
	public <T> T get(Object key, Class<T> clazz) {
		return doc.get(key, clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getInteger(java.lang.Object)
	 */
	@Override
	public Integer getInteger(Object key) {
		return doc.getInteger(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getInteger(java.lang.Object, int)
	 */
	@Override
	public int getInteger(Object key, int defaultValue) {
		return doc.getInteger(key, defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getLong(java.lang.Object)
	 */
	@Override
	public Long getLong(Object key) {
		return doc.getLong(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getDouble(java.lang.Object)
	 */
	@Override
	public Double getDouble(Object key) {
		return doc.getDouble(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getString(java.lang.Object)
	 */
	@Override
	public String getString(Object key) {
		return doc.getString(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getBoolean(java.lang.Object)
	 */
	@Override
	public Boolean getBoolean(Object key) {
		return doc.getBoolean(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getBoolean(java.lang.Object, boolean)
	 */
	@Override
	public boolean getBoolean(Object key, boolean defaultValue) {
		return doc.getBoolean(key, defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getObjectId(java.lang.Object)
	 */
	@Override
	public ObjectId getObjectId(Object key) {
		return doc.getObjectId(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#getDate(java.lang.Object)
	 */
	@Override
	public Date getDate(Object key) {
		return doc.getDate(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#toJson()
	 */
	@Override
	public String toJson() {
		return doc.toJson();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#toJson(org.bson.json.JsonWriterSettings)
	 */
	@Override
	public String toJson(JsonWriterSettings writerSettings) {
		return doc.toJson(writerSettings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#toJson(org.bson.codecs.Encoder)
	 */
	@Override
	public String toJson(Encoder<Document> encoder) {
		return doc.toJson(encoder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#toJson(org.bson.json.JsonWriterSettings,
	 * org.bson.codecs.Encoder)
	 */
	@Override
	public String toJson(JsonWriterSettings writerSettings, Encoder<Document> encoder) {
		return doc.toJson(writerSettings, encoder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#size()
	 */
	@Override
	public int size() {
		return doc.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return doc.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return doc.containsValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return doc.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#get(java.lang.Object)
	 */
	@Override
	public Object get(Object key) {
		return doc.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object put(String key, Object value) {
		return doc.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		return doc.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ?> map) {
		doc.putAll(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#clear()
	 */
	@Override
	public void clear() {
		doc.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#keySet()
	 */
	@Override
	public Set<String> keySet() {
		return doc.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#values()
	 */
	@Override
	public Collection<Object> values() {
		return doc.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return doc.entrySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return doc.equals(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#hashCode()
	 */
	@Override
	public int hashCode() {
		return doc.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bson.Document#toString()
	 */
	@Override
	public String toString() {
		return doc.toJson();
	}

}
