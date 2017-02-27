package com.leader.spring04.autowritedtest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.leader.spring04.autowritedtest");
		HelloWorldBean4 hello=context.getBean(HelloWorldBean4.class);
		hello.hello();
	}
}
