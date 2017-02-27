package leader04.ioc;

import leader04.logic.test1.UserService;
import leader04.logic.test2.HelloWorld1;
import leader04.logic.test3.UserServcie2;

public class Main {
	public static void main(String[] args) {
		
		AnnotationContext context = new AnnotationContext("leader04");
		context.parseComponent();
		HelloWorld1 hello = context.getBean(HelloWorld1.class);
		hello.sayHello();
		UserServcie2 service2 = context.getBean(UserServcie2.class);
		System.out.println(service2.getDao());
		UserService service = context.getBean(UserService.class);
		System.out.println("泛型自动注入"+service.getDao());
	}
}
