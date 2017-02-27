package leader04.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 订单服务器
 * 
 * @author zss
 */
@Component
public class OrderService {
	@Autowired
	private ApplicationEventPublisher publisher;
	/**
	 * 发送订单
	 * @param order
	 */
	public void productionOrder(Order order) {
		System.out.println(order.getUname() + " 产生了一个订单");
		publisher.publishEvent(order);
	}
}
