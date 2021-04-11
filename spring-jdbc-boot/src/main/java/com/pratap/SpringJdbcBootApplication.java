package com.pratap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pratap.model.Account;
import com.pratap.repository.AccountRepository;

@SpringBootApplication
public class SpringJdbcBootApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcBootApplication.class, args);
	}

	@Autowired
	AccountRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// repo.createAccount(new Account(1,"jack", 5000));
		// repo.createAccount(new Account(2,"jones", 50000));
		
		// repo.updateAccountBalance(1, 10000);
		
		// repo.updateNameById(1, "jimi");
		
		// repo.updateAccountByName("jones", 20000);
		
		// repo.deleteAccountById(2);
		
		List<Account> accounts = repo.getAllAccounts();
		System.out.println(accounts);
		
		Account account1 = repo.getAccountById(1);
		System.out.println(account1);
		
		Account accountJimi = repo.getAccountByName("jimi");
		System.out.println(accountJimi);
	}

}
