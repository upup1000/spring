package com.leader.spring08.test03;
/**
 * 对于长时间、可分段的任务，
 * 如果也采用一个大的事务一次性提交，则一旦失败代价太大了。
 * 最典型的例子就是：大批量的数据导入，你总不能因为一条数据错了而将其它插入全部回滚吧！
 * @author zss
 *
 */
public class MyServiceA {
	public void dobusiness() {
		queryLargRecords();
		calcData();
		updateMatchedRecords();
	}
    
	public void queryLargRecords() {

	}

	public void calcData() {

	}

	public void updateMatchedRecords() {

	}
}
