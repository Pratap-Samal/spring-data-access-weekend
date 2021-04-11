package com.pratap.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pratap.model.Account;

public class AccountRepositoryNamedImpl implements AccountRepositoryNamed {

	@Autowired
	NamedParameterJdbcTemplate template;
	
	@Override
	public int createAccount(Account acc) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accid", 3);
		paramMap.put("accname", "joy");
		paramMap.put("balance", 10000);
		
		return template.update("INSERT INTO ACCOUNT VALUES(:accid, :accname, :balance)", paramMap);
	}

}
