package com.abel;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.abel.dao.AccountDao;
import com.abel.dao.StudentDao;
import com.abel.entity.Account;
import com.abel.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles("test")
public class HibernateTest {
	private static final Logger logger = Logger.getLogger(HibernateTest.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private StudentDao studentDao;
    
    /** 
     * 测试hibernate配置是否成功
     * 测试Dao是否有效
    * @Title: saveAccount 
    * @Description: TODO   
    * @return void   
    * @throws 
    */
    @Test
    @Transactional
    public void saveAccount(){
    	Session session = sessionFactory.getCurrentSession();
    	Account account1 = new Account();
    	account1.setName("abel");
    	Account account2 = new Account();
    	account2.setName("karen");
    	long id1 = (Long)session.save(account1);//测试hibernate配置是否成功
    	long id2 = accountDao.save(account2);//测试Dao是否有效
    	logger.debug("save account and id1="+id1+", id2="+id2);
    }
    
    @Test
    @Transactional
    public void count(){
    	Student entity = new Student();
    	entity.setName("abel");
    	studentDao.save(entity);
    	long count = studentDao.getCount();
    	logger.debug("数据库中数据的个数为："+count);    	
    }
}
