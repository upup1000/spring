package leader.http.handler;


import leader.http.request.Request;
import leader.http.response.Response;
/**
 * 消息 请求处理 
 * @author zss
 */
public interface Handler {
	public void service(Request request, Response response);  
	  
    public void doGet(Request request, Response response);  
  
    public void doPost(Request request, Response response);  
}
