package com.pratap.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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


	@Override
	public List<Account> getAllAccounts() {
		return template.query("SELECT * FROM ACCOUNT", new RowMapper<Account>() {
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account acc = new Account();
				acc.setAccid(rs.getInt(1));
				acc.setName(rs.getString(2));
				acc.setBalance(rs.getDouble(3));

				return acc;
			}		
		});
	}


	@Override
	public Account getAccountById(int accid) {
		return template.query("SELECT * FROM ACCOUNT WHERE ACCID=?", 
				new ResultSetExtractor<Account>() {
					public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
						Account acc = new Account();
						if(rs.next())
							acc.setAccid(rs.getInt(1));
							acc.setName(rs.getString(2));
							acc.setBalance(rs.getDouble(3));
						
						return acc;
					}
	
				}
				,accid);
	}


	@Override
	public Account getAccountByName(String name) {
		AccountOneRowCallbackHandler rch = new AccountOneRowCallbackHandler();
		template.query("SELECT * FROM ACCOUNT WHERE NAME=?", rch , name);
		return rch.getAccount();
	}


	@Override
	public int[] bulkInsertAccounts(List<Account> accounts) {


		return template.batchUpdate("INSERT INTO ACCOUNT VALUES(?, ?, ?)", 
				new BatchPreparedStatementSetter() {
			
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Account acc = accounts.get(i);
						ps.setInt(1, acc.getAccid());
						ps.setString(2, acc.getName());
						ps.setDouble(3, acc.getBalance());
						
					}
					public int getBatchSize() {
						return accounts.size();
					}
				});
	}


	@Override
	public int createAccountAndGetPrimaryKey(Account acc) {
			KeyHolder kh = new GeneratedKeyHolder();
			
			template.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement("INSERT INTO ACCOUNT(NAME,BALANCE) VALUES(?, ?)", 	PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, acc.getName());
					ps.setDouble(2, acc.getBalance());
					return ps;
				}
			}, kh);

		return kh.getKey().intValue();
	}




}
