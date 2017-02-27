package com.leader.spring07.test01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan("com.leader.spring07.test01")
public class TxSpringConfig {

	@Bean
	public PlatformTransactionManager getTransactionManager() {
		DataSourceTransactionManager txMan = new DataSourceTransactionManager();
		txMan.setDataSource(getDataSource());
		System.out.println("open PlatformTransactionManager");
		return txMan;
	}

	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.1.251:3306/spring_test");
		dataSource.setUsername("root");
		dataSource.setPassword("root123");
		System.out.println("open datasource");
		return dataSource;
	}
}
