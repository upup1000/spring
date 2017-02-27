package leader.fio.impl;

import java.util.List;

import leader.bean.User;
import leader.fio.IUserOption;
/**
 * xml 用户 操作
 * @author zss
 */
public class XMLUserOption  implements IUserOption{

	@Override
	public boolean saveUser(User user) {
		return false;
	}

	@Override
	public boolean deleteUser(String uname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User selectUser(String uname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectUsers(String likeName,boolean onlyValidUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
