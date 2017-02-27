package leader.fio.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import leader.bean.User;
import leader.fio.IUserOption;
import leader.util.FileUtil;
import leader.util.ObjSerializableUtil;

/**
 * 二进制 实现
 * @author zss
 */
public class BinaryUserOption implements IUserOption {
	@Override
	public boolean saveUser(User user) {
		try {
			byte[] data = ObjSerializableUtil.obj2Byte(user);
			return FileUtil.addFile(data,user.getUserName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String uanme) {
		return FileUtil.delFile(uanme);
	}

	@Override
	public boolean updateUser(User user) {
		return saveUser(user);
	}

	@Override
	public User selectUser(String uname) {
		try {
			byte[] data = FileUtil.getFile(uname);
			return ObjSerializableUtil.byte2Obj(data, User.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUsers(String likeName,boolean onlyValidUser) {
		List<byte[]> datas=FileUtil.getLikeFile(likeName);
		List<User> users=new ArrayList<>();
		for(byte[] by:datas)
		{
			try {
				users.add(ObjSerializableUtil.byte2Obj(by, User.class));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
