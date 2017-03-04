package edu.ldcollege.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ldcollege.bean.LoginUser;
import edu.ldcollege.domain.LdUser;
import edu.ldcollege.domain.LdUserExample;
import edu.ldcollege.mapping.LdUserMapper;

@Component
public class UserService {

	@Autowired
	private LdUserMapper userMapper;

	public LdUser getUser(LoginUser user) {
		LdUserExample ex = new LdUserExample();
		ex.createCriteria().andLoginNameEqualTo(user.getUname()).andPwdEqualTo(user.getPwd())
				.andClassidEqualTo(user.getClassId()).andLessionidEqualTo(user.getLessonId());
		List<LdUser> users = userMapper.selectByExample(ex);
		if (users == null || users.size() == 0) {
			return null;
		}
		return users.get(0);
	}
}
