package leader.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import leader.bean.User;
import leader.bean.UserSession;
import leader.bean.UserSessionManager;
import leader.fio.IUserOption;
import leader.fio.proxy.UserOptionFactory;
import leader.fio.proxy.UserOptionProxy;

public class UserService {
	/**
	 * 代理模式 实现 存储
	 */
	private UserOptionProxy proxy;
	private static UserService service = new UserService();
	public static UserService getInstance() {
		return service;
	}
	private UserService() {
		Properties pps = new Properties();
		try {
			pps.load(UserService.class.getResourceAsStream("config.property"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int type = Integer.parseInt(pps.getProperty("save_type"));
		IUserOption option = UserOptionFactory.create(type);
		proxy = new UserOptionProxy(option);
	}
	/**
	 * 如果密码不对，返回的UserSession对象里sessionId为空，客户端可以依次判断，参照UserSession.isValid方法
	 * 
	 * @param userName
	 * @param md5EncodedPassword
	 * @return
	 */
	public UserSession login(String userName, String md5EncodedPassword) {
		UserSession session = new UserSession();
		session.setCreateTime(System.currentTimeMillis());
		User user = proxy.selectUser(userName);
		if (user != null && user.getPassword().equals(md5EncodedPassword)) {
			session.setUserId(user.getUserId());
			session.setUserName(user.getUserName());
			session.setSessionId(user.getUserId() + user.getUserName());
		}
		return session;
	}
	public boolean createUser(User user) {
		return proxy.saveUser(user);
	}

	public boolean deleteUser(String uname) {
		return proxy.deleteUser(uname);
	}

	public boolean disableUser(String uname) {
		return proxy.deleteUser(uname);
	}

	public List<User> queryUsers(String userNamePrex, boolean onlyValidUser) {
		return proxy.selectUsers(userNamePrex, onlyValidUser);
	}

	public User queryUser(String userName) {
		return proxy.selectUser(userName);
	}



	public boolean checkSession(String sessionId) {
		UserSession session = UserSessionManager.getSession(sessionId);
		if (session != null || !session.isValid()) {
			return false;
		}
		return true;
	}
}
