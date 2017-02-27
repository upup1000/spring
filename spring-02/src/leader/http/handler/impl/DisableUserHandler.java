package leader.http.handler.impl;

import leader.http.handler.AbsHttpHandler;
import leader.http.request.Request;
import leader.http.response.Response;
import leader.service.UserService;

public class DisableUserHandler extends AbsHttpHandler {

	@Override
	public void doGet(Request request, Response response) {
		String uname = request.getParamter("userName");
		if (UserService.getInstance().checkSession(request.getRemortIp())) {
			boolean success = UserService.getInstance().disableUser(uname);
			response.write("Disable " + uname + " " + success);
		}
	}

	@Override
	public void doPost(Request request, Response response) {
		// TODO Auto-generated method stub

	}

}
