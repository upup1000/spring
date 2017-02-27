package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.test.dao.UserDaoImpl;

@Configuration
@ComponentScan("com.test")
//一定要设置exposeProxy=true 将代理放入上下文中
@EnableAspectJAutoProxy(exposeProxy = true)
public class MyTestConfig {

	@Bean
	public UserDaoImpl getDao()
	{
		return new UserDaoImpl();
	}
}
