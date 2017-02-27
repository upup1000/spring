package com.leader.spring05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leader.spring05.bean.Course;
import com.leader.spring05.dao.CoureseDao;

@Component
public class MyCoureseService {
    @Autowired
	private CoureseDao dao;
    public void printAllCourese()
    {
    	List<Course> conuses=dao.getAllCourese();
    	for(Course course:conuses)
    	{
    		System.out.println("结果:"+course);
    	}
    }
}
