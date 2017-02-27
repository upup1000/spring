package leader04.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 发现新vip用户
 * 
 * @author zss
 */
@Component
public class VIPDetector {
	@Autowired
	private VipUserService userService;
    /**
     * 监听大于500的订单 
     * @param demoEvnt
     */
	@EventListener(value = {Order.class}, condition = "#demoEvnt.money > 500")
	public void orderListenter(Object demoEvnt) {
		Order order=(Order)demoEvnt;
		System.out.println("发现订单大于500的用户 "+ order.getUname());
		Vip vip=new Vip();
		vip.setUname(order.getUname());
		userService.createVip(vip);
	}
}
