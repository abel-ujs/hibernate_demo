package com.abel.utils.dao;

import java.util.List;

/** 
* @ClassName: QueryResult 
* @Description: TODO 
* @author abel_ujs@163.com
* @date 2015年5月6日 上午10:40:04 
* @param <T> 
*/
public class QueryResult<T> {
	
	/** 
	* @Fields resultList : 
	*/ 
	private List<T> resultList;
	
	/** 
	* @Fields totalCount : 
	*/ 
	private long totalCount;

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	

}
