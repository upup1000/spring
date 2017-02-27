package com.leader.spring04.fanxing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				"com.leader.spring04.fanxing");
		UserService1 hello = context.getBean(UserService1.class);
		System.out.println(hello.getDao().getClass());
		ResolvableType type1=ResolvableType.forClass(UserService1.class);
		System.out.println("type is"+type1.as(AbstractService1.class).getGeneric(0).resolve());
	}
}
