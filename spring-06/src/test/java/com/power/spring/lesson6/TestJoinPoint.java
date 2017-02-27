package com.power.spring.lesson6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leader.lesson6.config.MySpringConfig;
import com.leader.lesson6.service.UserService;
/**
 * @author zss
 */
public class TestJoinPoint {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.findProxy();
        System.out.println("userService = " + userService);
    }

}
