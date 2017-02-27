package leader.fio.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import leader.bean.User;
import leader.fio.IUserOption;

/**
 * 用户操作代理  正式环境
 * @author zss
 */
@Profile(value="release")
@Configurable
@PropertySource(value = "classpath:config_release.property",ignoreResourceNotFound=false)
@Component
@Lazy
public class UserOptionProxy_Release implements IOptionProxy{
	@Autowired
	IUserOption option;
	public boolean saveUser(User user) {
		System.out.println("正式环境保存用户"+user);
		return option.saveUser(user);
	}
	public boolean deleteUser(String userName) {
		System.out.println("正式环境删除用户"+userName);
		return option.deleteUser(userName);
	}

	public boolean updateUser(User user) {
		System.out.println("正式环境更新用户"+user);
		return option.updateUser(user);
	}
	
	public User selectUser(String userName) {
		System.out.println("正式环境查询用户"+userName);
		return option.selectUser(userName);
	}
	
	public List<User> selectUsers(String likeName,boolean onlyValidUser) {
		return option.selectUsers(likeName, onlyValidUser);
	}

}
