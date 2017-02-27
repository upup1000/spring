package spring.services;

import org.springframework.stereotype.Component;

@Component("myTestService2")
public class MyTestService2 {
	public MyTestService2()
	{
    System.out.println("MyTestService2 craeated "+this);
	}
	public void doBusinessAAA(String[] args,boolean suc)
	{
		 
	}
	
	public void doBusinessBBB(boolean suc)
	{
		 
	}
	public void doBusinessCCC()
	{
		 
	}
}
