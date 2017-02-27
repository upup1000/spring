package com.test.dao;

public class UserDaoImpl {
	public void updateUser() {
		System.out.println("update user");
//		Object proxy = AopContext.currentProxy();
//		((UserDaoImpl) proxy).deleteUser();
	}

	public void deleteUser() {
		System.out.println("delete user");
	}

}
