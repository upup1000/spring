package com.leader.spring05.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("com.leader.spring05")
@Profile(value="app-test")//测试环境
public class TestSpringCconfig {
	@Bean
	public DataSource getDataSource() {
		DataSource datasouce=Mockito.mock(DataSource.class);
		Connection con=Mockito.mock(Connection.class);
		Statement stmt=Mockito.mock(Statement.class);
		ResultSet reset=Mockito.mock(ResultSet.class);
		try {
			Mockito.when(datasouce.getConnection()).then(t->{return con;});
			Mockito.when(con.createStatement()).thenReturn(stmt);
			Mockito.when(reset.getInt(1)).thenReturn(1);
			Mockito.when(reset.getString(2)).thenReturn("mocktest");
			Mockito.when(reset.getInt(3)).thenReturn(88);
			Mockito.doReturn(reset).when(stmt).executeQuery("select * from course");
			Mockito.when(reset.next()).thenReturn(true).thenReturn(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasouce;
	}
}
