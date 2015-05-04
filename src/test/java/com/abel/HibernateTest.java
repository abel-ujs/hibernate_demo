package com.abel;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.abel.entity.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@TransactionConfiguration(defaultRollback = true)
public class HibernateTest {
	private static final Logger logger = Logger.getLogger(HibernateTest.class);
    @Autowired
    SessionFactory sessionFactory;
    
    @Test
    @Transactional
    public void saveAccount(){
    	Session session = sessionFactory.getCurrentSession();
    	Account account = new Account();
    	account.setName("abel");
    	long id = (Long)session.save(account);
    	logger.debug("save account and id="+id);
    }
}
