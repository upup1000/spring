package aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect2 {
	public MyAspect2()
	{
		System.out.println("MyAspect2 created ");
	}
	@Around("execution(* spring.services.MyDemoService3.*(..))")
	public void traceBusiness(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("before enter method "+jp.getSignature().getName());
		jp.proceed(jp.getArgs());
		System.out.println("after enter method "+jp.getSignature().getName());

	}
}
