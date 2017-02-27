package leader04.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * vip服务
 * @author zss
 */
@Component
public class VipUserService {
	@Autowired
	private ApplicationEventPublisher publisher;
	public void createVip(Vip vip)
	{
		System.out.println("创建一个vip "+vip.getUname());
		publisher.publishEvent(vip);
	}
}
