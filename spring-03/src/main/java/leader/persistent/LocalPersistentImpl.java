package leader.persistent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地持久化操作
 * 
 * @author zss
 */
public class LocalPersistentImpl implements IDataPersistent {
	private String basePath;
	private String suffix;

	public LocalPersistentImpl(String basePath, String suffix) {
		this.basePath = basePath;
		this.suffix = suffix;
		init();
	}

	/**
	 * 初始化创建目录 如果目录不存在
	 * 
	 * @return
	 */
	public boolean init() {
		return createIfNotExist(basePath);
	}

	public boolean createIfNotExist(String path) {
		boolean bFlag = false;
		try {
			File file = new File(path);
			if (!file.exists()) {
				if (file.isDirectory()) {
					file.mkdirs();
				} else {
					file.createNewFile();
				}

			}
			bFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 保存用户 数据
	 * 
	 * @param data
	 * @param fileName
	 * @return
	 */
	public boolean addFile(byte[] data, String fileName) {
		Path path = Paths.get(basePath.toString() + File.separator + fileName + suffix);
		try {
			Files.write(path, data, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean delFile(String fileName) {
		boolean bFlag = false;
		Path path = Paths.get(basePath.toString() + File.separator + fileName + suffix);
		try {
			Files.deleteIfExists(path);
			bFlag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 获得文件数据
	 * 
	 * @param fileName
	 */
	public byte[] getFile(String fileName) {
		Path path = Paths.get(basePath.toString() + File.separator + fileName + suffix);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得文件数据
	 * 
	 * @param fileName
	 */
	public List<byte[]> getLikeFile(String fileName) {
		List<File> list = getMathFiles(fileName);
		if (list == null) {
			return null;
		}
		List<byte[]> datas = new ArrayList<>();
		for (File file : list) {
			Path path = Paths.get(file.getAbsolutePath());
			try {
				datas.add(Files.readAllBytes(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return datas;
	}

	public List<File> getMathFiles(String fileName) {
		List<File> list = new ArrayList<>();
		File file = new File(basePath.toString());
		File files[] = file.listFiles();
		if (files == null) {
			return null;
		}
		for (File f : files) {
			String name = f.getName();
			if (name.indexOf(fileName) != -1) {
				list.add(f);
			}
		}
		return list;
	}
}
