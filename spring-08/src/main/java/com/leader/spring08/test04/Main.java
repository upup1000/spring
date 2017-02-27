package com.leader.spring08.test04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leader.spring08.test04.config.Test04Config;
import com.leader.spring08.test04.service.MyAopServiceTest;
import com.leader.spring08.test04.service.MyTransactionServiceTest;



public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(Test04Config.class);
		MyAopServiceTest service = ctx.getBean(MyAopServiceTest.class);
		service.test();
		MyTransactionServiceTest transServiceTest=ctx.getBean(MyTransactionServiceTest.class);
		transServiceTest.addTest();
	}
}
