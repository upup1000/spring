package leader04.event;

import org.springframework.stereotype.Component;

@Component
public class EmailService {
	public void sendEmail(Vip vip)
	{
		System.out.println("向 vip用户 "+vip.getUname()+" 发出一封邮件");
	}
}
