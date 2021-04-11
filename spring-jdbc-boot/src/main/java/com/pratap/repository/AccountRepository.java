package com.pratap.repository;

import java.util.List;

import com.pratap.model.Account;

public interface AccountRepository {
	public int deleteAccountById(int accid);
	public int createAccount(Account acc);
	public int createAccountAndGetPrimaryKey(Account acc);
	public int updateAccountBalance(int accid, double balance);
	public int updateNameById(int accid, String name);
	public int updateAccountByName(String name, double balance);
	
	public List<Account> getAllAccounts();
	public Account getAccountById(int accid);
	public Account getAccountByName(String name);
	
	

	public int[] bulkInsertAccounts(List<Account> accounts);
	
	
}
