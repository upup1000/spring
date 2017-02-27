package leader04.event;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("leader04.event");
		OrderService service=context.getBean(OrderService.class);
		Order order=new Order();
		order.setDate(new Date());
		order.setId(1);
		order.setMoney(5000);
		order.setUname("leader us");
		service.productionOrder(order);
		
	    order=new Order();
		order.setDate(new Date());
		order.setId(2);
		order.setMoney(100);
		order.setUname("zss");
		service.productionOrder(order);
	}
}
