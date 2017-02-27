package com.leader.spring07.test02.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
	@Transactional
	public void addNewUser(String name, int age) throws SQLException ;
	public void queryUser() throws SQLException ;
}
