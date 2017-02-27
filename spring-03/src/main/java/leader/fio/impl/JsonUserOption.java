package leader.fio.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import leader.bean.User;
import leader.fio.IUserOption;
import leader.persistent.IDataPersistent;

/**
 * json实现用户状态数据持久化操作
 * @author zss
 */
public class JsonUserOption implements IUserOption {
	private IDataPersistent per;
	public JsonUserOption(IDataPersistent per)
	{
		this.per=per;
	}
	public boolean saveUser(User user) {
		JSONObject obj = (JSONObject) JSON.toJSON(user);
		return per.addFile(obj.toJSONString().getBytes(), user.getUserName());
	}

	public boolean deleteUser(String userName) {
		return per.delFile(userName + "");
	}
	
	public boolean updateUser(User user) {
		return saveUser(user);
	}
	
	public User selectUser(String userName) {
		byte[] data = per.getFile(userName);
		if (data == null) {
			return null;
		}
		return JSON.parseObject(new String(data), User.class);
	}

	public List<User> selectUsers(String likeName, boolean onlyValidUser) {
		List<byte[]> datas = per.getLikeFile(likeName);
		List<User> users = new ArrayList<>();
		for (byte[] by : datas) {
			users.add(JSON.parseObject(new String(by), User.class));
		}
		return users;
	}
}
