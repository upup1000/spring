import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.services.MyDemoService3;

public class Test6 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MySpringConfig.class);
		MyDemoService3 mybean = (MyDemoService3) ctx.getBean(MyDemoService3.class);
		mybean.findDBxxx();
		ctx.close();
	}

}
