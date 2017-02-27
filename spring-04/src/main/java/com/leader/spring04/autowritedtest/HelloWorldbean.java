package com.leader.spring04.autowritedtest;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("helloBean")
public class HelloWorldbean {
	private String myName;

	public HelloWorldbean() {
		System.out.println(new Date() + " create " + this);
	}

	public void hello() {
		System.out.println(new Date() + "hellow " + myName + "@ " + this);
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
	
	
}
