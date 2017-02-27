package leader.http.handler.impl;

import java.util.Date;

import leader.bean.User;
import leader.http.handler.AbsHttpHandler;
import leader.http.request.Request;
import leader.http.response.Response;
import leader.service.UserService;
import leader.util.MD5Util;
/**
 * 创建用户处理
 * @author zss
 */
public class CreateUserHandler extends AbsHttpHandler {

	@Override
	public void doGet(Request request, Response response) {
		String uname = request.getParamter("userName");
		String pwd = request.getParamter("pwd");
		User user = new User();
		user.setEnabled(true);
		user.setPassword(MD5Util.getMD5(pwd));
		user.setRegDate(new Date());
		user.setUserId(System.currentTimeMillis());
		user.setUserName(uname);
		boolean success = UserService.getInstance().createUser(user);
		response.write("create user success"+success);
	}

	@Override
	public void doPost(Request request, Response response) {
		doGet(request, response);
	}

}
