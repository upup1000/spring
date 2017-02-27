

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("spring.services,aop.aspects")
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
//@EnableAspectJAutoProxy(proxyTargetClass=true)
@Configurable
public class MySpringConfig {
	
public MySpringConfig()
{
	System.out.println("MySpringConfig create d ");
}
}
