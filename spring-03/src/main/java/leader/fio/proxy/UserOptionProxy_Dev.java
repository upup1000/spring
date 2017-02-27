package leader.fio.proxy;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import leader.bean.User;
import leader.util.AppContextUtil;
/**
 *  用户操作代理   测试环境
 * @author zss
 *
 */
@Profile(value="dev")
@Configurable
@PropertySource(value = "classpath:config_dev.property",ignoreResourceNotFound=false)
@Component
@Lazy
public class UserOptionProxy_Dev implements IOptionProxy {

	public boolean saveUser(User user) {
		System.out.println("测试环境保存用户"+user);
		return true;
	}
	public boolean deleteUser(String userName) {
		System.out.println("测试环境删除用户"+userName);
		return true;
	}

	public boolean updateUser(User user) {
		System.out.println("测试环境更新用户"+user);
		return true;
	}
	
	public User selectUser(String userName) {
		System.out.println("测试环境查询用户"+userName);
		return AppContextUtil.getBean(User.class);
	}
	
	public List<User> selectUsers(String likeName,boolean onlyValidUser) {
		return Collections.emptyList();
	}

}
