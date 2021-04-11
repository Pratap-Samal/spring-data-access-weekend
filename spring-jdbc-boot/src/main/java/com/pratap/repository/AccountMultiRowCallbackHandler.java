package com.pratap.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.pratap.model.Account;

public class AccountMultiRowCallbackHandler implements RowCallbackHandler {
	
	List<Account> accounts = new ArrayList<>();
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {

		Account acc = new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3) );
		accounts.add(acc);

	}
	
	public List<Account>  getAccounts(){
		return accounts;
	}

}
