package com.leader.spring04.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventReceiver {
	/**
	 * @EventListener 表示监听 所有的事件
	 * @param demoEvnt
	 *//*
		 * @EventListener public void handlerEvent(Object demoEvnt) {
		 * System.out.println("received event"+demoEvnt); }
		 */

	// /**
	// * @EventListener 只监听 MyDemoEvent.class,MyDemoEvent2.class 两个类的事件
	// * @param demoEvnt
	// */
	// @EventListener(value={MyDemoEvent.class,MyDemoEvent2.class})
	// public void handlerEvent(Object demoEvnt)
	// {
	// System.out.println("received event"+demoEvnt);
	// }

	/**
	 * @EventListener 只监听 MyDemoEvent.class,MyDemoEvent2.class 两个类的事件 同时
	 *                使用SPEL表达式 过滤 只监听id大于4的
	 * @param demoEvnt
	 */
	@EventListener(value={MyDemoEvent.class,MyDemoEvent2.class},condition="#demoEvnt.id > 4")
	 public void handlerEvent(Object demoEvnt)
	 {
	 System.out.println("received event"+demoEvnt);
	 }

//	/**
//	 * 监听指定类型的事件
//	 * 
//	 * @param demoEvnt
//	 */
//	@EventListener
//	public void handlerEvent(MyDemoEvent demoEvnt) {
//		System.out.println("received event" + demoEvnt);
//	}
//
//	@EventListener
//	public void handlerEvent(MyDemoEvent2 demoEvnt) {
//		System.out.println("received event" + demoEvnt);
//	}
}
