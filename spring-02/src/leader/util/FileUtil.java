package leader.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	private static final String basePath = "e:" + File.separator + "users";
	private static final String Suffix = ".user";
	/**
	 * 初始化创建目录 如果目录不存在
	 * @return
	 */
	public static boolean init() {
		return createIfNotExist(basePath);
	}
	public static boolean createIfNotExist(String path) {
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
	 * @param data
	 * @param fileName
	 * @return
	 */
	public static boolean addFile(byte[] data, String fileName) {
		Path path = Paths.get(basePath.toString() + File.separator + fileName + Suffix);
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
	public static boolean delFile(String fileName) {
		boolean bFlag = false;
		Path path = Paths.get(basePath.toString() + File.separator + fileName + Suffix);
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
	 * @param fileName
	 */
	public static byte[] getFile(String fileName) {
		Path path = Paths.get(basePath.toString() + File.separator + fileName + Suffix);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得文件数据 
	 * @param fileName
	 */
	public static List<byte[]> getLikeFile(String fileName) {
		List<File> list=getMathFiles(fileName);
		if(list==null)
		{
			return null;
		}
		List<byte[]> datas=new ArrayList<>();
		for(File file:list)
		{
			Path path =	Paths.get(file.getAbsolutePath());
			try {
				datas.add(Files.readAllBytes(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return datas;
	}
	public static List<File> getMathFiles(String fileName)
	{
		List<File> list=new ArrayList<>();
		File file=new File(basePath.toString());
		File files[]=file.listFiles();
		if(files==null)
		{
			return null;
		}
		for(File f:files)
		{
			String name=f.getName();
			if(name.indexOf(fileName)!=-1)
			{
				list.add(f);
			}
		}
		return list;
	}
	public static void main(String[] args) throws IOException {
		Path path = Paths.get(basePath.toString());
		FileStore st=Files.getFileStore(path);
		
	}
}
