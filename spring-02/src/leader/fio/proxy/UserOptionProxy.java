package leader.fio.proxy;

import java.util.List;

import leader.bean.User;
import leader.fio.IUserOption;

/**
 * 用户操作代理
 * @author zss
 */
public class UserOptionProxy implements IUserOption {
	IUserOption option;

	public UserOptionProxy(IUserOption option) {
		this.option = option;
	}

	@Override
	public boolean saveUser(User user) {
		return option.saveUser(user);
	}

	@Override
	public boolean deleteUser(String userName) {
		return option.deleteUser(userName);
	}

	@Override
	public boolean updateUser(User user) {
		return option.updateUser(user);
	}

	@Override
	public User selectUser(String userName) {
		return option.selectUser(userName);
	}

	@Override
	public List<User> selectUsers(String likeName,boolean onlyValidUser) {
		return option.selectUsers(likeName, onlyValidUser);
	}

}
