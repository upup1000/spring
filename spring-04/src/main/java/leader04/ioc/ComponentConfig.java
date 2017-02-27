package leader04.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件配置
 * @author zss
 */
public class ComponentConfig {
	/**
	 * 组件的class
	 */
	private Class<?> clazz;
	/**
	 * 需要自动注入的字段名
	 */
	private List<String> fieldNames= new ArrayList<>();

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void addField(String fieldName) {
		fieldNames.add(fieldName);
	}
	public List<String> getFieldNames() {
		return fieldNames;
	}


}
