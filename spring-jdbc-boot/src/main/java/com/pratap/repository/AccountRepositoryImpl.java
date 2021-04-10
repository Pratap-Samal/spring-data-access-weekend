package com.pratap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.pratap.model.Account;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
	
	@Autowired
	JdbcTemplate template;
	
	
	@Override
	public int deleteAccountById(int accid) {	
		return template.update("DELETE FROM ACCOUNT WHERE ACCID="+accid);
	}


	@Override
	public int createAccount(Account acc) {
		return template.update("INSERT INTO ACCOUNT VALUES(?, ?, ?)", 
									acc.getAccid(), acc.getName(), acc.getBalance());
	}


	@Override
	public int updateAccountBalance(int accid, double balance) {
		return template.update("UPDATE ACCOUNT SET BALANCE=? WHERE ACCID=?", 
							new Object[] {balance, accid}, new int[] {Types.DOUBLE, Types.INTEGER});
	}


	@Override
	public int updateNameById(int accid, String name) {
		return template.update("UPDATE ACCOUNT SET NAME=? WHERE ACCID=?", 
						new PreparedStatementSetter() {
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setString(1, name);
								ps.setInt(2, accid);
							}
						});
	}


	@Override
	public int updateAccountByName(String name, double balance) {
		return template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("UPDATE ACCOUNT SET BALANCE=? WHERE NAME=?");
				
				ps.setDouble(1, balance);
				ps.setString(2, name);
				return ps;
			}
		});
	}

}
