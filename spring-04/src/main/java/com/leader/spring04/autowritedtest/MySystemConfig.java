package com.leader.spring04.autowritedtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySystemConfig {

	@Bean HelloWorldbean anotherHelloBean()
	{
		return new HelloWorldbean();
	}
}
