package spring.services;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Component
public class MyDemoService3 {

	public void findDBxxx() {
		System.out.println("logic  findDBxxx executed ");
//		if(true)
//		{
//        throw new RuntimeException(" call eerr");
//		}
		Object proxy = AopContext.currentProxy();
		((MyDemoService3) proxy).findDByyy();
		Advised advised=(Advised)proxy;
		System.out.println(advised.getTargetSource());
		System.out.println(advised.getTargetSource().isStatic());
		this.findDByyy();
	};

	public void findDByyy() {
		System.out.println("logic  findDByyy executed ");
	};
}
