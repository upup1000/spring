package leader.http.handler.impl;

import leader.bean.UserSession;
import leader.bean.UserSessionManager;
import leader.http.handler.AbsHttpHandler;
import leader.http.request.Request;
import leader.http.response.Response;
import leader.service.UserService;
import leader.util.MD5Util;

/**
 * 登陆消息处理类
 * @author zss
 */
public class LoginHandler extends AbsHttpHandler {
	@Override
	public void doGet(Request request, Response response) {
		String uname = request.getParamter("userName");
		String pwd = request.getParamter("pwd");
		pwd=MD5Util.getMD5(pwd);
		UserSession session = UserService.getInstance().login(uname, pwd);
		if (session.getSessionId() == null) {
			response.write("user " + uname + "Non-existent ");
		} else {
			session.setSessionId(request.getRemortIp());
			UserSessionManager.addSession(session);
			response.write("Welcome " + uname);
		}
	}
	@Override
	public void doPost(Request request, Response response) {
		doGet(request, response);
	}

}
