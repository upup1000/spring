package remeberme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import remeberme.domain.AppUser;
import remeberme.domain.AppUserExample;
import remeberme.mapping.AppUserMapper;

@Component
public class UserServcie {

	@Autowired
	private AppUserMapper userMapper;

	public AppUser findBySso(String sso) {
		AppUserExample ex = new AppUserExample();
		ex.createCriteria().andSsoIdEqualTo(sso);
		return userMapper.selectByExample(ex).get(0);

	}

}
