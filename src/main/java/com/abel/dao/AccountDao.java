package com.abel.dao;

import java.util.List;

import com.abel.entity.Account;

public interface AccountDao {
	
	public Account getAccountById(long id);
	
	public List<Account> getAllAccounts();
	
	public void save(Account account);
	
	public void delete(Account account);
	
	public void update(Account account);

}
