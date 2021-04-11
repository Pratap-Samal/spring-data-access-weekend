package com.pratap.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.pratap.model.Account;

public class AccountOneRowCallbackHandler implements RowCallbackHandler {
	int accid;
	String name;
	double balance;

	@Override
	public void processRow(ResultSet rs) throws SQLException {

		this.accid = rs.getInt(1);
		this.name = rs.getString(2);
		this.balance = rs.getDouble(3);

	}
	
	public Account getAccount() {
		return new Account(accid, name, balance);
	}

}
