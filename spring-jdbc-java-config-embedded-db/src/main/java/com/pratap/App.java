package com.pratap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		JdbcTemplate template = context.getBean("template", JdbcTemplate.class);
		System.out.println(template);
		
		System.out.println(template.getDataSource());

	}

}
