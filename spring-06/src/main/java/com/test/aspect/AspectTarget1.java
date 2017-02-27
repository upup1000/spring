package com.test.aspect;

import org.springframework.stereotype.Component;

/**
 * 被代理的对象
 * @author zss
 */
@Component("test")
public class AspectTarget1 implements TestInterface{
	public void test1(String args)
	{
		System.out.println("this AspectTarget1 test1");
	}
	public void test2(String args)
	{
		
	}
}
