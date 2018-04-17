package com.wonder.mongodb.api;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;

public class DistinctResults<T> {

	private MongoCursor<T> resultsc;
	private DistinctIterable<T> resultsd;
	private MongoCursor<T> temp;
	private long size;
	
	public DistinctResults(DistinctIterable<T> distinct, MongoCursor<T> mongoCursor) {
		this.resultsc = mongoCursor;
		this.resultsd = distinct;
		size = -1;
	}
	
	public boolean hasNext() {
		return resultsc.hasNext();
	}
	
	public DistinctResult<T> next() {
		return new DistinctResult<T>(resultsc.next());
	}
	
	public long size() {
		if(size == -1) {
			size = 0;
			temp = resultsd.iterator();
			while(temp.hasNext()) {
				temp.next();
				size++;
			}
			return size;
		} else {
			return size;
		}
	}
	
	final class ResultSet extends AbstractSet<T> {

		private MongoCursor<T> resultsc;
		private DistinctIterable<T> resultsd;
		
		public ResultSet(DistinctIterable<T> distinct) {
			this.resultsd = distinct;
			resultsc = resultsd.iterator();
		}
		
		@Override
		public Iterator<T> iterator() {
			return resultsc;
		}

		@Override
		public int size() {
			return (int) size;
		}
             
    }
	
	public Set<T> resultSet() {
		return new ResultSet(resultsd);
	}
	
}
