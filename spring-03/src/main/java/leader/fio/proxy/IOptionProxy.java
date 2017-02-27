package leader.fio.proxy;

import java.util.List;

import leader.bean.User;

public interface IOptionProxy{
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user);
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(String uname);
	/**
	 * 更新用户 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	/**
	 * 查询用户
	 * @param userId
	 * @return
	 */
	public User selectUser(String uname);
	/**
	 * 模糊查询用户
	 * @param likeName
	 * @return
	 */
	public List<User> selectUsers(String likeName,boolean onlyValidUser);
}
