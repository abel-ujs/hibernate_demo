package com.abel.utils.dao;

import java.util.ArrayList;
import java.util.List;

public class QueryResult<T> {
	private int total;
	private List<T> result = new ArrayList<T>();
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	
	

}
