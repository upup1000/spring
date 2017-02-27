package leader.persistent;

import java.util.List;

/**
 * 持久化接口
 * @author zss
 */
public interface IDataPersistent {
	/**
	 * 保存用户 数据
	 * 
	 * @param data
	 * @param fileName
	 * @return
	 */
	public boolean addFile(byte[] data, String fileName) ;

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean delFile(String fileName);

	/**
	 * 获得文件数据
	 * 
	 * @param fileName
	 */
	public byte[] getFile(String fileName);

	/**
	 * 获得文件数据
	 * 
	 * @param fileName
	 */
	public List<byte[]> getLikeFile(String fileName);
}
