package com.leader.spring08.test01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leader.spring08.test01.config.Test01Config;
import com.leader.spring08.test01.domain.TBUser;
import com.leader.spring08.test01.service.UserService;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(Test01Config.class);
		UserService service = ctx.getBean(UserService.class);
		TBUser user=new TBUser();
		user.setAge(2);
		user.setId(1002L);
		user.setName("1111");
		service.createUser(user);
		ctx.close();
	}
}
