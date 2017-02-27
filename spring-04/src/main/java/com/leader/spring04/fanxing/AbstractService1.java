package com.leader.spring04.fanxing;

import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService1<T>{

	@Autowired
	private AbstractDao1<T> dao;
	
	public boolean saveDomain(T domain)
	{
		System.out.println("check domain obj"+domain);
		doCheckDomain(domain);
		System.out.println("save domain obj"+ domain);
		dao.craeteDomainObj(domain);
		return true;
	}
	
	public AbstractDao1<T> getDao()
	{
		return dao;
	}
	protected void doCheckDomain(T domain) {
		
	}
}
