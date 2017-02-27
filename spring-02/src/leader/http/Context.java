package leader.http;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import leader.http.handler.AbsHttpHandler;


public class Context {
	private static Map<String,AbsHttpHandler> contextMap = new HashMap<String,AbsHttpHandler>();  
    public static String contextPath = ""; 
    
    public static Document loadXml(String filename){
        Document doc = null;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(new File(filename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }
    public static void load(){  
        try{  
        	Document document=loadXml(Context.class.getResource("/").getPath()+"web.xml");
            Element element= document.getRootElement(); 
            contextPath= element.attribute("context").getStringValue();
          	List<Element> servers= document.selectNodes("/httpServer/handler");
              for(Element el:servers)
              {
            	  String clazz=el.element("handler-class").getStringValue();
            	  String url=el.element("url-pattern").getStringValue();
            	  Class<?> cls = Class.forName(clazz);
            	  Object newInstance = cls.newInstance(); 
            	  if(newInstance instanceof AbsHttpHandler){  
                    contextMap.put(contextPath+url, (AbsHttpHandler)newInstance);  
                }  
              }
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     *  
     * @param key 
     * @return 
     */  
    public static AbsHttpHandler getHandler(String key){  
        return contextMap.get(key);  
    }  
    public static void main(String[] args) {
    	Context.load();
	}
      
}
