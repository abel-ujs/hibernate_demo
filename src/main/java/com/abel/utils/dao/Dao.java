package com.abel.utils.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.abel.utils.Page;


/** 
* @ClassName: Dao 
* @Description: TODO 
* @author abel_ujs@163.com
* @date 2015年5月6日 上午10:22:49 
* @param <T> 
*/
/** 
* @ClassName: Dao 
* @Description: TODO 
* @author abel_ujs@163.com
* @date 2015年5月6日 上午10:42:39 
* @param <T> 
*/
public interface Dao<T> {

	/** 
	* @Title: save 
	* @Description: TODO
	* @param entity   
	* @return void   
	* @throws 
	*/
	public void save(T entity);

	/** 
	* @Title: update 
	* @Description: TODO
	* @param entity   
	* @return void   
	* @throws 
	*/
	public void update(T entity);

	/** 
	* @Title: delete 
	* @Description: TODO
	* @param entityids   
	* @return void   
	* @throws 
	*/
	public void delete(Serializable... entityIds);
	
	/** 
	* @Title: find 
	* @Description: TODO
	* @param entityId
	* @return   
	* @return T   
	* @throws 
	*/
	public T find(Serializable entityId);
	
	/** 
	* @Title: getCount 
	* @Description: TODO
	* @return   
	* @return long   
	* @throws 
	*/
	public long getCount();
	

	/** 
	* @Title: getScrollData 
	* @Description: TODO
	* @param firstIndex
	* @param maxResult
	* @param whereHql
	* @param params
	* @param orderBy
	* @return   
	* @return QueryResult<T>   
	* @throws 
	*/
	public QueryResult<T> getScrollData(int firstIndex,int maxResult,String whereHql,Object[] params,LinkedHashMap<String,String> orderBy);


	public QueryResult<T> getScrollData(int firstIndex,int maxResult);
	
}
