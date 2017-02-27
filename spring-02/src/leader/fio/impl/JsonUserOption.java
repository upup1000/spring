package leader.fio.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import leader.bean.User;
import leader.fio.IUserOption;
import leader.util.FileUtil;

/**
 * json实现用户状态数据持久化操作
 * 
 * @author zss
 */
public class JsonUserOption implements IUserOption {
	@Override
	public boolean saveUser(User user) {
		JSONObject obj = (JSONObject) JSON.toJSON(user);
		return FileUtil.addFile(obj.toJSONString().getBytes(), user.getUserName());
	}

	@Override
	public boolean deleteUser(String userName) {
		return FileUtil.delFile(userName + "");
	}

	@Override
	public boolean updateUser(User user) {
		return saveUser(user);
	}

	@Override
	public User selectUser(String userName) {
		byte[] data = FileUtil.getFile(userName);
		if (data == null) {
			return null;
		}
		return JSON.parseObject(new String(data), User.class);
	}

	@Override
	public List<User> selectUsers(String likeName, boolean onlyValidUser) {
		List<byte[]> datas = FileUtil.getLikeFile(likeName);
		List<User> users = new ArrayList<>();
		for (byte[] by : datas) {
			users.add(JSON.parseObject(new String(by), User.class));
		}
		return users;
	}
}
