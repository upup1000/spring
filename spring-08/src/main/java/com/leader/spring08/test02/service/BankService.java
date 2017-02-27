package com.leader.spring08.test02.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 模拟银行存钱
 * @author zss
 */
@Component
@Transactional
public class BankService {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveMoney(int userId,int money) {
		saveToDB(userId,money);
		BankService target = (BankService) AopContext.currentProxy();
		target.sendMsg(userId,money);
	}
	
    public void saveToDB(int userId,int money)
    {
    	System.out.println("用户"+ userId+" 存入了"+money);
    }
    
	@Transactional(propagation = Propagation.NESTED)
	public void sendMsg(int userId,int money) {
		System.out.println("您与XXX日期 存入"+money+"人民币,本短信不做入账凭证,很确认的告诉你 真的已经存入了");
	}
}
