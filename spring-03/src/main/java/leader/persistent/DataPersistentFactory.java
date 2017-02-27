package leader.persistent;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 数据持久化类 工厂
 * @author zss
 */
@Component
public class DataPersistentFactory implements FactoryBean<IDataPersistent> {
	/**
	 * 根据配置的spring03.save_type 决定文件的存储位置
	 */
	@Value("${spring03.save_type:1}")
	private int save_type;
	/**
	 * 本地文件的存储位置
	 */
	@Value("${spring03.save_loacl_path}")
	private String save_loacl_path;
	/**
	 * ftp文件的存储位置
	 */
	@Value("${spring03.save_ftp_path}")
	private String save_ftp_path;
	/**
	 * 文件后缀名
	 */
	@Value("${spring03.save_suffix}")
	private String suffix;

	@Override
	public IDataPersistent getObject() throws Exception {
		switch (save_type) {
		case 1:
			return new LocalPersistentImpl(save_loacl_path, suffix);
		case 2:
			return new FTPPersistentImpl(save_ftp_path, suffix);
		default:
			break;
		}
		return new LocalPersistentImpl(save_loacl_path, suffix);
	}

	@Override
	public Class<?> getObjectType() {
		return IDataPersistent.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
