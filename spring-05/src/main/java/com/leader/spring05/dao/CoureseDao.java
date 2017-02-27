package com.leader.spring05.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leader.spring05.bean.Course;

@Component
public class CoureseDao {

	@Autowired
	private DataSource datasource;
	public List<Course> getAllCourese()
	{
		try {
			Connection conn=datasource.getConnection();
			ResultSet reset=conn.createStatement().executeQuery("select * from course");
			List<Course> list=new ArrayList<>();
			while(reset.next())
			{
				Course course=new Course();
				course.setId(reset.getInt(1));
				course.setName(reset.getString(2));
				course.setMark(reset.getInt(3));
				list.add(course);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
