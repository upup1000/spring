package leader.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = "leader.**")
@EnableRedisHttpSession
public class AppTest extends SpringBootServletInitializer {
	@Value("${spring.session.timeout}")
	private Integer maxInactiveIntervalInSeconds;

	@Bean
	public RedisOperationsSessionRepository sessionRepository(RedisConnectionFactory factory) {
		RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(factory);
		//设置默认超时时间
		System.out.println("set session timeout "+maxInactiveIntervalInSeconds);
		sessionRepository.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
		return sessionRepository;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AppTest.class, args);
	}
}
