package leader04.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 用户事件监听
 * 
 * @author zss
 */
@Component
public class UserCareServcie {
	@Autowired
	private EmailService emailService;

	@EventListener(value = { Vip.class })
	public void orderListenter(Object ovip) {
		Vip vip = (Vip) ovip;
		emailService.sendEmail(vip);
	}
}
