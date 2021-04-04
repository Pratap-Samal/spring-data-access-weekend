package com.pratap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class App {

	public static void main(String[] args) {
			ClassPathXmlApplicationContext context = 
					new ClassPathXmlApplicationContext("app-context.xml");
			
			JdbcTemplate template = context.getBean("template", JdbcTemplate.class);
			
			System.out.println(template);
	}

}
