package com.leader.spring07.test01;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.leader.spring07.test01.config.TxSpringConfig;
import com.leader.spring07.test01.service.UserService;

public class Main3 {

	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(TxSpringConfig.class);
		PlatformTransactionManager txMan=cxt.getBean(PlatformTransactionManager.class);
		System.out.println("+++++++++++++++事务开启前++++++++++++++++++++++++++++++");
//		ThreadLocalUtil.dumphreadLocals();
		DefaultTransactionDefinition def=new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(300);
		TransactionStatus status=txMan.getTransaction(def);
		System.out.println("+++++++++++++++事务开启后++++++++++++++++++++++++++++++");
//		ThreadLocalUtil.dumphreadLocals();
		UserService userservice = cxt.getBean(UserService.class);
		userservice.addNewUser("djn",30);
//		userservice.queryUser();
		txMan.commit(status);
		System.out.println("+++++++++++++++事务结束后++++++++++++++++++++++++++++++");
		ThreadLocalUtil.dumphreadLocals();
		
		cxt.close();
	}
}
