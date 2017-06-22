package leader.security;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	http .headers().xssProtection().xssProtectionEnabled(false);//关闭 XSS防御 默认是开启的
		//1  /manager/** 需要 manager的角色才能访问， /manager/createuser 路径则额外需要 op_createuser的授权才能访问
		http.headers().httpStrictTransportSecurity();
		http.authorizeRequests().
		antMatchers("/manager/createUser").hasAuthority("op_createuser").
		antMatchers("/manager/**").hasAnyRole("MANAGER").
		and().formLogin();
		
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("**/js/**", "**/css/**", "**/images/**", "**/**/favicon.ico");

	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() throws IOException {
		Properties userPropers = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/users.dat"));
		Properties newUsers = new Properties();
		for (Entry entry : userPropers.entrySet()) {
			String user = (String) entry.getKey();
			String value = (String) entry.getValue();
			String[] valueItems = value.split(",");
			String password = valueItems[0];
			String newValue = password + "," + valueItems[1] + "," + valueItems[2];
			// users.put("user","pass,ROLE_USER,enabled");
			newUsers.setProperty(user, newValue);
		}
		return new InMemoryUserDetailsManager(newUsers);
	}
}
