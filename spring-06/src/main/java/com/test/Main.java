package com.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.aspect.TestInterface;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MyTestConfig.class);
//		AspectTarget1 target1 = applicationContext.getBean(AspectTarget1.class);
//		target1.test1("111");
		TestInterface target1 = (TestInterface)applicationContext.getBean("test");
		target1.test1("111");
	}
}
