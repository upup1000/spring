package com.leader.spring08.test03;

import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 方案1
 * 保存事务中间的状态 一旦回滚不会回滚全部
 * 把大的事务划分成多个小的内嵌事务，
 * 每个内嵌事务执行成功后刷新save point，
 * 如果全部成功则一次性提交，如果中间失败了则回滚则上一个save point，
 * 同时继续下一个内嵌事务，不怕将前面尚未提交的其它事务操作全部回滚
 * 和PROPAGATION_REQUIRED相比，好处就是事务提交的次数少了，可以一次性写入
 * @author zss
 *
 */
public class MyServiceB {
	@Transactional(propagation=Propagation.REQUIRED)
	public void dobusiness() {
		MyServiceB target=(MyServiceB)AopContext.currentProxy();
		target.queryLargRecords();
		target.calcData();
		target.updateMatchedRecords();
	}
    @Transactional(propagation=Propagation.NESTED)
	public void queryLargRecords() {

	}
    @Transactional(propagation=Propagation.NESTED)
	public void calcData() {

	}
    @Transactional(propagation=Propagation.NESTED)
	public void updateMatchedRecords() {

	}
}
