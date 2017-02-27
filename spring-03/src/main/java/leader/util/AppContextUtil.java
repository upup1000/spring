package leader.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Component 工具类
 * @author zss
 */
@Component
public class AppContextUtil {
   private static ApplicationContext context = null;
   @EventListener
   public void setApplicationContext(ContextRefreshedEvent eve) {
       context = eve.getApplicationContext();
   }
   public static <T> T getBean(Class<T> clazz) {
       return context.getBean(clazz);
   }
}