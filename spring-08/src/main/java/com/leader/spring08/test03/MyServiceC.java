package com.leader.spring08.test03;

import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 方案2
 * 这时可以考虑划分为小段的PROPAGATION_REQUIRED事务进行，多次独立提交事务 確定不能一次性寫入
 * @author zss
 *
 */
public class MyServiceC {
	@Transactional(propagation=Propagation.REQUIRED)
	public void dobusiness() {
		MyServiceC target=(MyServiceC)AopContext.currentProxy();
		target.queryLargRecords();
		target.calcData();
		target.updateMatchedRecords();
	}
    @Transactional(propagation=Propagation.REQUIRED)
	public void queryLargRecords() {

	}
    @Transactional(propagation=Propagation.REQUIRED)
	public void calcData() {

	}
    @Transactional(propagation=Propagation.REQUIRED)
	public void updateMatchedRecords() {

	}
}
