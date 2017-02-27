package com.leader.spring08.test04.service;

import org.springframework.stereotype.Component;

@Component
public class MyTransactionServiceTest {
	/**
	 * addTest方法 已经开启事务
	 */
	public void addTest()
	{
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StringBuffer sb = new StringBuffer();
		for (StackTraceElement s : stackTrace) {
			sb.append(s.getClassName() + "," + s.getMethodName() + "," + s.getLineNumber() + "\r\n");
		}
		System.out.println(sb.toString());
		System.out.println(Thread.currentThread().getName());
	}
}
