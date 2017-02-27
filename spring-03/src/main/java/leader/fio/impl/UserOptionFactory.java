package leader.fio.impl;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import leader.fio.IUserOption;
import leader.persistent.IDataPersistent;

/**
 * 用户信息操作  工厂类 
 * @author zss
 */
@Component
public class UserOptionFactory implements FactoryBean<IUserOption> {
	/**
	 * 根据配置的spring03.save_format值来决定使用哪种方式保存 如果配置不存在默认1 json方式  
	 */
	@Value("${spring03.save_format:1}")
	private int type;
	@Autowired 
	private IDataPersistent dataper;
	@Override
	public IUserOption getObject() throws Exception {
		switch (type) {
		case 1:
			return new JsonUserOption(dataper);
		case 2:
			return new BinaryUserOption(dataper);
		case 3:
			return new XMLUserOption(dataper);
		default:
			break;
		}
		return new JsonUserOption(dataper);
	}
	@Override
	public Class<?> getObjectType() {
		return IUserOption.class;
	}
	@Override
	public boolean isSingleton() {
		return true;
	}
}
