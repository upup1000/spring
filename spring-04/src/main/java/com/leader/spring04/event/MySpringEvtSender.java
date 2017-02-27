package com.leader.spring04.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MySpringEvtSender {
	@Autowired
	private ApplicationEventPublisher publisher;

	public void createEvt() {
		MyDemoEvent evnt = new MyDemoEvent();
		evnt.setId(111111);
		evnt.setMsg("MyDemoEvent");
		publisher.publishEvent(evnt);
		System.out.println("published event" + evnt);

		MyDemoEvent2 evnt2 = new MyDemoEvent2();
		evnt2.setId(22222);
		evnt2.setMsg("MyDemoEvent2");
		publisher.publishEvent(evnt2);
		System.out.println("published event" + evnt2);
	}
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.leader.spring04.event");
		MySpringEvtSender bean=context.getBean(MySpringEvtSender.class);
		bean.createEvt();
	}
}
