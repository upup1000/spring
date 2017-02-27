package com.leader.spring07.test03;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import com.leader.spring07.test03.config.T3Config;
import com.leader.spring07.test03.service.UserService;

public class Main {
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(T3Config.class);
		final PlatformTransactionManager txMan = cxt.getBean(PlatformTransactionManager.class);
		UserService userservice = cxt.getBean(UserService.class);
		try {
			userservice.addNewUser("test",55);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
