package leader04.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leader04.scan.ClassScanUtil;

/**
 * 模拟 注解上下文
 * @author zss
 */
public class AnnotationContext {
	/**
	 * 扫描到的所有组件类
	 */
	private Set<Class<?>> scanClasses;
	/**
	 * 所有的组件配置
	 */
	private Map<Class<?>, ComponentConfig> compMap = new HashMap<>();
    /**
     * 要扫描的包的路径
     * @param basePack
     */
	public AnnotationContext(String basePack) {
		this.scanClasses = ClassScanUtil.getClasses(basePack);
	}

	public void parseComponent() {
		ComponentParseUtil.configParser(this);
	}

	public <T> T getBean(Class<T> clazz) {
		ComponentConfig config = compMap.get(clazz);
		if (config == null) {
			return null;
		}
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		setComponentField(t);
		return t;
	}

	/**
	 * 设置字段的值
	 * @param object
	 */
	public void setComponentField(Object object) {
		Class<?> childGenericClass = getGenericClass(object.getClass());
		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			ComponentConfig config = compMap.get(clazz);
			List<String> fieldNames = config.getFieldNames();
			if (fieldNames.size() != 0) {
				for (String key : fieldNames) {
					Class<?> fileClass = null;
					Field filed = null;
					try {
						filed = clazz.getDeclaredField(key);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					Type filedType = filed.getGenericType();
					// 如果字段是泛型类型的
					if (filedType instanceof ParameterizedType) {
						ParameterizedType parType = (ParameterizedType) filedType;
						Type genericType = parType.getActualTypeArguments()[0];
						// 如果类型是未知的 T 那么取子类的具体类型
						if (genericType instanceof TypeVariable) {
							fileClass = getRightChildClass(filed.getType(), childGenericClass);
						} else {
							Class<?> generc = null;
							try {
								generc = Class.forName(genericType.getTypeName());
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
							fileClass = getRightChildClass(filed.getType(), generc);
						}
						setFiledValue(object, filed, fileClass);
					} else if (!(filedType instanceof GenericArrayType)) {
						setFiledValue(object, filed, filed.getType());
					}
				}
			}
		}
	}

	/**
	 * 设置字段值
	 * @param obj
	 * @param filed
	 * @param valuec
	 */
	private void setFiledValue(Object obj, Field filed, Class<?> valuec) {
		Object value = getBean(valuec);
		filed.setAccessible(true);
		try {
			filed.set(obj, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得具体的泛型参数
	 * @param clazz
	 */
	public Class<?> getGenericClass(Class<?> calss) {
		if (calss.getSuperclass() == Object.class) {
			return null;
		}
		Type type = calss.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType parType = (ParameterizedType) type;
			Type genericType = parType.getActualTypeArguments()[0];
			// 如果类型是未知的 T 那么取子类的具体类型
			if (genericType instanceof TypeVariable) {
				return null;
			} else {
				String classname = genericType.getTypeName();
				try {
					return Class.forName(classname);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 找到一个合适的子类
	 * @param clazz
	 *            父类class
	 * @param genericClazz泛型中具体的参数class
	 * @return
	 */
	public Class<?> getRightChildClass(Class<?> clazz, Class<?> genericClazz) {
		Collection<ComponentConfig> configs = compMap.values();
		for (ComponentConfig config : configs) {
			Class<?> child = config.getClazz();
			if (clazz.isAssignableFrom(child)) {
				Type type = child.getGenericSuperclass();
				if (type instanceof ParameterizedType) {
					ParameterizedType parType = (ParameterizedType) type;
					String classname = parType.getActualTypeArguments()[0].getTypeName();
					if (classname.equals(genericClazz.getName())) {
						return child;
					}
				}
			}
		}
		return null;
	}

	public void registerComponent(ComponentConfig config) {
		compMap.put(config.getClazz(), config);
	}

	public Set<Class<?>> getScanClasses() {
		return scanClasses;
	}

}
