package com.leader.spring05.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.leader.spring05")
@Profile(value="app-release")//正式环境
@PropertySource("classpath:db.properties")
public class ReleaseSpringConfig {
	@Value(value = "${conn.url}")
	private String connectionUrl;

	@Value(value = "${conn.username}")
	private String username;

	@Value(value = "${conn.password}")
	private String password;

	@Value(value = "${conn.driver}")
	private String driverName;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(connectionUrl, username, password);
		dataSource.setDriverClassName(driverName);
		return dataSource;
	}
}
