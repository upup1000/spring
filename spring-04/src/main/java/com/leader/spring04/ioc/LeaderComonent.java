package com.leader.spring04.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LeaderComonent {
	public enum CompType {
		singLeton, nonsingLeton, customer
	};

	CompType injectBy() default CompType.singLeton;

	String name();

	boolean threadsafe() default false;
}
