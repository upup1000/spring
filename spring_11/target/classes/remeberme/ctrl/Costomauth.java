package remeberme.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
public class Costomauth extends UsernamePasswordAuthenticationFilter{

	public Authentication attemptAuthentication(HttpServletRequest request,  
	        HttpServletResponse response) throws AuthenticationException {
	    return super.attemptAuthentication(request, response);  
	}  
}
