package com.leader.spring04.event;
/**
 * 自定义事件
 * @author zss
 */
public class MyDemoEvent {
	private int id;
	private String msg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
