package leader.http.request;

import java.net.URI;
/**
 * http请求接口
 * @author zss
 */
public interface Request {
	public final static String GET = "GET";  
    public final static String POST = "POST";  
  
    public String getParamter(String param);  
  
    public String getRemortIp();
    public String getMethod();  
  
    public URI getReuestURI();  
  
    public void initRequestHeader();  
      
    public void initRequestParam();  
  
    public void initRequestBody();  
  
    public String getRequestBody();  
}
