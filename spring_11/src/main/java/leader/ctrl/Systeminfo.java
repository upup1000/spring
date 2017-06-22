package leader.ctrl;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Systeminfo {
	@RequestMapping("/curuser")
	public Principal user(Principal user)
	{
		System.out.println(user);
		return user;
	}
}
