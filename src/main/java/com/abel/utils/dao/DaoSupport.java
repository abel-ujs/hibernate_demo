package com.abel.utils.dao;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.abel.utils.Page;
import com.abel.utils.Reflections;

/**
 * @ClassName: DaoSupport
 * @Description: TODO
 * @author abel_ujs@163.com
 * @date 2015年5月6日 下午10:12:33
 * @param <T>
 */
@Transactional
public class DaoSupport<T> implements Dao<T> {

	@Autowired
	public HibernateUtil hibernateUtil;

	private Class<T> entityClass = Reflections.getClassGenricType(
			this.getClass(), 0);

	public void save(T entity) {
		hibernateUtil.getSession().save(entity);
	}

	public void update(T entity) {
		// wait for change
		hibernateUtil.getSession().save(entity);

	}

	public void delete(Serializable... entityIds) {
		for (Serializable id : entityIds) {
			T entity = (T) hibernateUtil.getSession().get(entityClass, id);
			if (entity != null) {
				hibernateUtil.getSession().delete(entity);
			}
		}
	}

	public T find(Serializable entityId) {
		T entity = (T) hibernateUtil.getSession().get(entityClass, entityId);
		if (entityId == null)
			throw new RuntimeException(this.entityClass.getName()
					+ ":传入的id不能为空!");
		return entity;
	}

	public long getCount() {
		Query createQuery = hibernateUtil.getSession().createQuery(
				"select count(" + getCountField(entityClass) + ") from "
						+ getEntityName() + " o");
		return (Long) createQuery.uniqueResult();
	}

	public QueryResult<T> getScrollData(int firstIndex, int maxResult,
			String whereHql, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		String queryString = "select o from "+getEntityName()+" o "+( whereHql==null || "".equals(whereHql)?"":" where "+whereHql)
				+getOrderByHql(orderBy);
		Query query = hibernateUtil.getSession().createQuery(queryString);
		query.setFirstResult(firstIndex );
		query.setMaxResults(maxResult);
		QueryResult<T> qr = new QueryResult<T>();
		qr.setResult(query.list());
		qr.setTotal((int) getCount());
		return qr;
	}
	
	public Page<T> getPage(Page<T> page,String whereHql,Object[] params,LinkedHashMap<String,String> orderBy){
		String queryString = "select o from "+getEntityName()+" o "+( whereHql==null || "".equals(whereHql)?"":" where "+whereHql)
				+getOrderByHql(orderBy);
		Query query = hibernateUtil.getSession().createQuery(queryString);
		query.setFirstResult(page.getFirstIndex() );
		query.setMaxResults(page.getPageSize());
		if(params!=null && params.length>0){
			int i=1;
			for(Object o: params){
				query.setParameter(i++, o);
			}
		}
		page.setTotal(getCount());
		page.setResult(query.list());
		return page;
	}
	public Page<T> getPage(Page<T> page){
		return getPage(page,null,null,null);
	}

	private String getOrderByHql(HashMap<String ,String> orderBy){
		StringBuilder orderHql = new StringBuilder("");
		if(orderBy != null && orderBy.size()>0){
			orderHql.append(" order by ");
			for(Map.Entry<String, String> entity : orderBy.entrySet()){
				orderHql.append("o.")
						.append(entity.getKey()).append(" ").append(entity.getValue()).append(",");
			}
			orderHql.deleteCharAt(orderHql.length()-1);
		}
		return orderHql.toString();
	}
	
	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx
	 * o语句BUG而增加,hibernate对此jpql解析后的sql为select
	 * count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * 
	 * @param <E>
	 * @param clazz
	 * @return
	 */
	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		try {
			// 判断方法上的注解
			// PropertyDescriptor[] propertyDescriptors =
			// Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			// for(PropertyDescriptor propertydesc : propertyDescriptors){
			// Method method = propertydesc.getReadMethod();
			// if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){
			// PropertyDescriptor[] ps =
			// Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
			// out = "o."+ propertydesc.getName()+ "." +
			// (!ps[1].getName().equals("class")? ps[1].getName():
			// ps[0].getName());
			// break;
			// }
			// }
			// 判断属性上的注解
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field != null
						&& field.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							field.getType()).getPropertyDescriptors();
					out = "o."
							+ field.getName()
							+ "."
							+ (!ps[1].getName().equals("class") ? ps[1]
									.getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * 获取实体名称
	 * 
	 * @return
	 */
	protected String getEntityName() {
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityName = entity.name();

		}
		return entityName;
	}

	public QueryResult<T> getScrollData(int firstIndex, int maxResult) {
		return this.getScrollData(firstIndex, maxResult,null,null,null);
	}

}
