package edu.ldcollege.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ldcollege.bean.LoginReult;
import edu.ldcollege.bean.LoginUser;
import edu.ldcollege.domain.LdUser;
import edu.ldcollege.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/loginsys")
	@ResponseBody
	public LoginReult login(HttpSession session, LoginUser user) {
		LdUser use1 = userService.getUser(user);
		LoginReult result = new LoginReult();
		if (use1 == null) {
			result.setState(1);
			result.setErrorinfo("用户不存在");
			return result;
		}
		session.setAttribute("user", use1);
		result.setToUrl("userinfo.html");
		return result;
	}

	/**
	 * 退出系统
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "logoutsys")
	@ResponseBody
	public LoginReult logout(HttpSession session) throws Exception {
		// 清除Session
		session.removeAttribute("user");
		session.invalidate();
		LoginReult result = new LoginReult();
		result.setToUrl("index.html");
		return result;
	}

}
