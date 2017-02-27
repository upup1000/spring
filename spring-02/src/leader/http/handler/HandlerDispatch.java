package leader.http.handler;
import java.io.IOException;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import leader.http.Context;
import leader.http.request.HttpRequest;
import leader.http.response.HttpResponse;  
/**
 * 根据路径转发到具体的handler
 * @author zss
 */
public class HandlerDispatch  implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		HttpRequest request = new HttpRequest(httpExchange);
		HttpResponse response = new HttpResponse(httpExchange);
		Handler handler = Context.getHandler(request.getReuestURI().getPath());
		if(handler==null)
		{
			response.write("404 not found");
			return;
		}
		handler.service(request, response);
	}

}
	