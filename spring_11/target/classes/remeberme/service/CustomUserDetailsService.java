package remeberme.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import remeberme.domain.AppUser;
import remeberme.domain.UserProfile;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserServcie userService;
	@Autowired
	private UserProfileService profileServer;

	@Override
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		AppUser user = userService.findBySso(ssoId);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(),
				user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(AppUser user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserProfile profile=profileServer.getUserProfiles(user.getId());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + profile.getType()));
		return authorities;
	}

}
