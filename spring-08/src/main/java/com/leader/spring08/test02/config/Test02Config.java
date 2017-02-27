package com.leader.spring08.test02.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.leader.spring08.test02")
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
//mybatis 掃描mapping接口 解析并生成代理
@MapperScan("com.leader.spring08.test02.mapping")
@ImportResource("classpath:dataSource.xml")
@EnableTransactionManagement
public class Test02Config {

	@Bean
	public PlatformTransactionManager getTransactionManager(DataSource live_datasource) {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(live_datasource);
		return tx;
	}
	
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource live_datasource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(live_datasource);
		return sqlSessionFactoryBean;
	}
	
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}
}
