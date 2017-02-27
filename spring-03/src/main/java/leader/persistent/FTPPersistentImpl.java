package leader.persistent;

import java.util.List;
/**
 * ftp远程存储实现
 * @author zss
 */
public class FTPPersistentImpl implements IDataPersistent{
	public FTPPersistentImpl(String basePath, String suffix) {
	}
	@Override
	public boolean addFile(byte[] data, String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delFile(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] getFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<byte[]> getLikeFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
