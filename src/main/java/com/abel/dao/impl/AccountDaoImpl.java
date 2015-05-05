package com.abel.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abel.dao.AccountDao;
import com.abel.entity.Account;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {
	
	private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public Account getAccountById(long id) {
		
		return (Account)getSession().get(Account.class, id);
	}

	public long save(Account account) {
		
		return (Long)getSession().save(account);
	}

	public void delete(Account account) {
		
		getSession().delete(account);

	}

	public void update(Account account) {
		
		getSession().merge(account);
	}
	
	private Session getSession(){
		
		return this.sessionFactory.getCurrentSession();
	}

	public List<Account> getAllAccounts() {
		Criteria criteria = getSession().createCriteria(Account.class);
		return criteria.list();
	}

}
