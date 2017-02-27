package leader.http.handler;

import leader.bean.UserSessionManager;
import leader.http.request.Request;
import leader.http.response.Response;

/**
 * 消息处理接口的实现 模板方法
 * 
 * @author zss
 *
 */
public abstract class AbsHttpHandler implements Handler {
	private UserSessionManager sessionM=new UserSessionManager();
	@Override
	public final void service(Request request, Response response) {
		request.initRequestHeader();
		request.initRequestParam();
		if (request.getMethod().equals(Request.GET)) {
			doGet(request, response);
		} else if (request.getMethod().equals(Request.POST)) {
			request.initRequestBody();
			doPost(request, response);
		}

	}

	@Override
	public abstract void doGet(Request request, Response response);

	@Override
	public abstract void doPost(Request request, Response response);

}
