package leader.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import leader.http.handler.HandlerDispatch;
/**
 * http服务启动
 * @author zss
 */
public class HttpService {
	private static Executor executor = Executors.newCachedThreadPool();
	protected static void buildHttpServer(int port) throws IOException {
		Context.load();  
		HttpServerProvider provider = HttpServerProvider.provider();  
	    HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(port),1000);//监听端口8080,能同时接 受100个请求  
        httpserver.createContext(Context.contextPath,new HandlerDispatch());   
        httpserver.setExecutor(executor);  
        httpserver.start();  
        System.out.println("server started"); 
	}

	public static void main(String[] args) {
		try {
			HttpService.buildHttpServer(987);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
