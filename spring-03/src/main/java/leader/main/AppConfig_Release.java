package leader.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import leader.bean.User;
import leader.service.UserService;
/**
 * 正式环境
 * @author zss
 */
@Component
public class AppConfig_Release {
	 @Autowired
    private Environment ent;
	public Environment getEnt() {
		return ent;
	}

	public void setEnt(Environment ent) {
		this.ent = ent;
	}

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active","dev");
		System.setProperty("spring.profiles.default","release");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("applicationContext2.xml");
		
		UserService service=ctx.getBean(UserService.class);
		User user=ctx.getBean(User.class);
		user.setUserId(System.currentTimeMillis());
		user.setUserName("zss11");
		user.setRegDate(new Date());
		user.setEnabled(true);
		user.setPassword("1111");
		service.createUser(user);
		service.deleteUser(user.getUserName());
		service.queryUser(user.getUserName());
		ctx.registerShutdownHook();
		System.out.println(JSON.class.getClassLoader());
	}
}
