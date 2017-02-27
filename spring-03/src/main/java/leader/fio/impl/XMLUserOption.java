package leader.fio.impl;

import java.util.List;

import leader.bean.User;
import leader.fio.IUserOption;
import leader.persistent.IDataPersistent;
/**
 * xml 用户 操作
 * @author zss
 */
public class XMLUserOption  implements IUserOption{
	private IDataPersistent per;
	public XMLUserOption(IDataPersistent per)
	{
		this.per=per;
	}
	public boolean saveUser(User user) {
		return false;
	}
	public boolean deleteUser(String uname) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public User selectUser(String uname) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> selectUsers(String likeName,boolean onlyValidUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
