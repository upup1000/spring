package spring.services;

import org.springframework.stereotype.Component;

@Component("myDemoService")
public class MyDemoService implements MyDemoServiceIntf {

	public MyDemoService()
	{
		System.out.println("MyDemoService created "+this);
	}
	@Override
	public void doBusinessBBB(boolean suc) {
		 
		
	}

}
