package com.leader.spring04.autowritedtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldBean4 {
	@Autowired
	private HelloWorldbean anotherHelloBean;
	private ArrayList<String> t;
	public HelloWorldBean4() {
		System.out.println(new Date() + " create " + this);
	}

	public void hello() {
		anotherHelloBean.hello();
		System.out.println(Arrays.toString(t.toArray()));
	}
}
