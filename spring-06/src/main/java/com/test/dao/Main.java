package com.test.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.MyTestConfig;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyTestConfig.class);
		UserDaoImpl dao1 = (UserDaoImpl) applicationContext.getBean(UserDaoImpl.class);
		dao1.updateUser();
	}
}
