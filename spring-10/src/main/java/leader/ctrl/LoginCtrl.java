package leader.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import leader.viewbean.OperateResult;
import leader.viewbean.UserInfoBean;

@Controller
public class LoginCtrl {
	@RequestMapping(value = "/login")
	@ResponseBody
	public OperateResult login(HttpServletRequest request, @Valid UserInfoBean user, BindingResult result) {
		OperateResult operresult = new OperateResult();
		if (result.hasErrors()) {
			operresult.setCode("1");
			Map<String,String> errorMap = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			operresult.setMsg(errorMap);
			return operresult;
		}
		user.setSessionId(request.getSession().getId());
		request.getSession().setAttribute("user", user);
		operresult.setMsg(user);
		System.out.println();
		return operresult;
	}

	@RequestMapping(value = "/userinfo")
	@ResponseBody
	public OperateResult userInfo(HttpServletRequest request) {
		UserInfoBean bean = (UserInfoBean) request.getSession().getAttribute("user");
		OperateResult operresult = new OperateResult();
		if(bean==null)
		{
			operresult.setCode("1");
			return operresult;
		}
		operresult.setMsg(bean);
		return operresult;
	}
}
