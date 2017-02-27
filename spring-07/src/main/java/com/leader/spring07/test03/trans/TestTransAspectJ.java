package com.leader.spring07.test03.trans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
@Component
@Aspect
public class TestTransAspectJ {
	@Autowired
	PlatformTransactionManager tm;
	
	@Around("execution(* com.leader.spring07.test03.service.*Service.add*(..)) || "
			+ "execution(* com.leader.spring07.test03.service.*Service.query*(..))")
	public void transReadAspect(ProceedingJoinPoint pjp) {
		DefaultTransactionDefinition def=new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		def.setTimeout(300);
		TransactionStatus status=tm.getTransaction(def);
		try {
		   pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			status.setRollbackOnly();
		} 
		tm.commit(status);
	}
	
}
