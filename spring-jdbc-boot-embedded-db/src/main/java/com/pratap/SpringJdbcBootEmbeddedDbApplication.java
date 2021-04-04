package com.pratap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringJdbcBootEmbeddedDbApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcBootEmbeddedDbApplication.class, args);
	}
	
	@Autowired
	JdbcTemplate template;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(template);
		System.out.println(template.getDataSource());
		
	}

}
