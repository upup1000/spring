package leader.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {

	@RequestMapping("/manager/createUser")
	public String createUser() {
		return "ok";
	}
}
