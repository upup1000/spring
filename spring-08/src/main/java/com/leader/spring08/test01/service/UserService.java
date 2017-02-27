package com.leader.spring08.test01.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leader.spring08.test01.domain.TBUser;
import com.leader.spring08.test01.mapping.TBUserMapper;

@Component
@Transactional
public class UserService {

	@Autowired
	private TBUserMapper userMapper;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void createUser(TBUser user) {
//		userMapper.insert(user);
		sqlSessionTemplate.insert("com.leader.spring08.test01.mapping.TBUserMapper.insert", user);
		System.out.println("==================");
	}

	public List<TBUser> getAllUsers() {
		return userMapper.selectByExample(null);
	}

}
