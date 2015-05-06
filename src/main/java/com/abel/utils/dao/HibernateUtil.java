package com.abel.utils.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
