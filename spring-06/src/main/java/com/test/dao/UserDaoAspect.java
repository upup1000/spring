package com.test.dao;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserDaoAspect {
	public UserDaoAspect() {
		System.out.println("UserDaoAspect created ");
	}
	@Before("execution(* com.test.dao.*.*(..))")
	public void testBefore(JoinPoint jp) {
		System.out.println("before joinpoint [  method "+ jp.getSignature().getName()+"]");

	}
}
