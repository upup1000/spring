package com.leader.spring07.test01;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.leader.spring07.test01.config.TxSpringConfig;
import com.leader.spring07.test01.service.UserService;

public class Main2 {
	
	
	/**
	 * 事务的隔离级别是 针对并发的事务之间的隔离程度 
	 * 事务的传播范围 针对单线程多个事务之间的处理
	 * @param args
	 * @throws SQLException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws SQLException, InterruptedException {
	    final AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(TxSpringConfig.class);
	    final PlatformTransactionManager txMan = cxt.getBean(PlatformTransactionManager.class);
		Thread thread=new Thread(new Runnable() {
			public void run() {
				UserService userservice = cxt.getBean(UserService.class);
				System.out.println("================start transaxtion"+Thread.currentThread().getName());
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				def.setTimeout(300);
				TransactionStatus status1 = txMan.getTransaction(def);
				try {
					userservice.addNewUser("zss", 30);
				} catch (SQLException e) {
					e.printStackTrace();
					txMan.rollback(status1);
					return;
				}
				txMan.commit(status1);
				System.out.println("================end transaxtion"+Thread.currentThread().getName());
			}
		});
		thread.start();
		Thread thread1=new Thread(new Runnable() {
			public void run() {
				UserService userservice = cxt.getBean(UserService.class);
				System.out.println("================start transaxtion"+Thread.currentThread().getName());
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				def.setTimeout(300);
				TransactionStatus status1 = txMan.getTransaction(def);
				try {
					userservice.addNewUser("zss", 30);
				} catch (SQLException e) {
					e.printStackTrace();
					txMan.rollback(status1);
					return;
				}
				txMan.commit(status1);
				System.out.println("================end transaxtion"+Thread.currentThread().getName());
			}
		});
		thread1.start();
		thread.join();
		thread1.join();
		cxt.close();
	}
}
