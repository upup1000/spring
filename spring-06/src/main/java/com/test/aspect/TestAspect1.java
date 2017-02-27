package com.test.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect1 {
	public TestAspect1() {
		System.out.println("TestAspect1 created ");
	}

	@Before("execution(* com.test.aspect.*.*(..))")
	public void testBefore(JoinPoint jp) {
		System.out.println("before joinpoint [  " + jp.getKind() + " ,declaringTypeName "
				+ jp.getSignature().getDeclaringTypeName() + "\r\n this " + jp.getThis().getClass().getName()
				+ "\r\n,target:" + jp.getTarget() + " , method " + jp.getSignature().getName() + ",args:"
				+ Arrays.toString(jp.getArgs()));

	}
}
