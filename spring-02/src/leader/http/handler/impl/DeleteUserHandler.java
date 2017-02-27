package leader.http.handler.impl;

import leader.http.handler.AbsHttpHandler;
import leader.http.request.Request;
import leader.http.response.Response;
import leader.service.UserService;

public class DeleteUserHandler extends AbsHttpHandler {

	@Override
	public void doGet(Request request, Response response) {
		String uname=request.getParamter("userName");
		if(UserService.getInstance().checkSession(request.getRemortIp()))
		{
			response.write("plase relogin ");
			return;
		}
		boolean success = UserService.getInstance().deleteUser(uname);
		response.write("dlete "+uname+":"+success);
	}

	@Override
	public void doPost(Request request, Response response) {
		// TODO Auto-generated method stub
		
	}

}
