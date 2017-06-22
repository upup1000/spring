package remeberme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import remeberme.domain.UserProfile;
import remeberme.mapping.UserProfileMapper;
@Component
public class UserProfileService {
	@Autowired
	private UserProfileMapper userMapper;

	public UserProfile getUserProfiles(long userId) {
		
		return userMapper.selectByPrimaryKey(userId);

	}
}
