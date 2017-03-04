package edu.ldcollege.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import edu.ldcollege.interceptor.LoginInterceptor;
@Component
public class Mvconfig extends WebMvcConfigurerAdapter {
	@Override  
    public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration reg=registry.addInterceptor(new LoginInterceptor());
		reg.addPathPatterns("/**");
    }  
}
