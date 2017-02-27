package com.leader.spring08.test01.testproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy {
	public <T> T newInstance(Class<T> mapperProxy) {
		return (T) Proxy.newProxyInstance(mapperProxy.getClassLoader(), new Class[] { mapperProxy }, new TestHandler());
	}

	public static void main(String[] args) {
		TestJDKProxy proxy = new TestJDKProxy();
		TestInerface interfaceImpl = proxy.newInstance(TestInerface.class);
		String string=interfaceImpl.test1();
		System.out.println(string);
	}
}

class TestHandler implements InvocationHandler {

	public TestHandler() {

	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method methodproxy = this.getClass().getMethod(method.getName(), method.getParameterTypes());
		return methodproxy.invoke(this, args);
	}
	public String test1() {
		return "hello";
	}

	public String test2() {
		return "test2";
	}
}

interface TestInerface {
	
	public String test1();
	public String test2();
}