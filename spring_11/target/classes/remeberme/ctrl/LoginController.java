package remeberme.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@RequestMapping(value = "/manager/logintest", method = RequestMethod.GET)
	public String loginPage() {	
		System.out.println("login");
		return "login success";
	}
	
	@RequestMapping("/manager/createUser")
	public String createUser() {
		return "ok";
	}
	
	@RequestMapping("/getcsrf")
	public String getCSRF(HttpServletRequest request) {
		CsrfToken csrfToken =(CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return csrfToken.getToken();
	}
}
