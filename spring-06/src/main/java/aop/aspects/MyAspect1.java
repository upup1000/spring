package aop.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect1 {
	public MyAspect1()
	{
		System.out.println("MyAspect1 created ");
	}
	
	@Before("execution(* spring.services.*.*(..))")
	public void traceBusiness() {
		System.out.println( "in spring advice ");

	}
	@Before("execution(* spring.services.*.*(..))")
	public void traceBusiness(JoinPoint jp) {
		System.out.println("joinpoint [  "+jp.getKind() + " ,declaringTypeName "
				+ jp.getSignature().getDeclaringTypeName()+"\r\n this "+jp.getThis().getClass().getName() + "\r\n,target:" + jp.getTarget() + " , method "
				+ jp.getSignature().getName() + ",args:" + Arrays.toString(jp.getArgs()));

	}
}
