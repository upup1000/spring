package com.leader.spring07.test02;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import com.leader.spring07.test02.config.TxSpringConfig;
import com.leader.spring07.test02.service.IUserService;


public class Main1 {
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(TxSpringConfig.class);
		final PlatformTransactionManager txMan = cxt.getBean(PlatformTransactionManager.class);
		IUserService userservice = cxt.getBean(IUserService.class);
		try {
			System.out.println("start call userservice.addNewUser");
			userservice.addNewUser("test",55);
			System.out.println("end call userservice.addNewUser");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
