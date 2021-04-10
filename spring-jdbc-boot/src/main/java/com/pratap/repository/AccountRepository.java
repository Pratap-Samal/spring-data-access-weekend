package com.pratap.repository;

import com.pratap.model.Account;

public interface AccountRepository {
	public int deleteAccountById(int accid);
	public int createAccount(Account acc);
	public int updateAccountBalance(int accid, double balance);
	public int updateNameById(int accid, String name);
	public int updateAccountByName(String name, double balance);
}
