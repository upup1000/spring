package com.leader.spring05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leader.spring05.config.ReleaseSpringConfig;
import com.leader.spring05.service.MyCoureseService;

public class Main {

	public static void main(String[] args) {
		//测试环境
//		System.setProperty("spring.profiles.default", "app-test");
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
//				TestSpringCconfig.class);
//		MyCoureseService userService = applicationContext.getBean(MyCoureseService.class);
//		userService.printAllCourese();
		
		//正式环境
		System.setProperty("spring.profiles.default", "app-release");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ReleaseSpringConfig.class);
		MyCoureseService userService = applicationContext.getBean(MyCoureseService.class);
		userService.printAllCourese();
	}
}
