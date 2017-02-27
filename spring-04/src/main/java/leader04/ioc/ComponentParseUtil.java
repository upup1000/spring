package leader04.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import annotation.ZAutoWrite;
import annotation.ZComponent;

/**
 * 组件解析
 * @author zss
 */
public class ComponentParseUtil {
	public static void configParser(AnnotationContext context) {
		if (context == null) {
			throw new RuntimeException("");
		}
		Set<Class<?>> scanClasses = context.getScanClasses();
		for (Class<?> clazz : scanClasses) {
			Annotation[] annotations = clazz.getAnnotationsByType(ZComponent.class);
			if (annotations == null || annotations.length == 0) {
				continue;
			}
			ComponentConfig config = new ComponentConfig();
			config.setClazz(clazz);
			context.registerComponent(config);
			for (int j = 0; j < annotations.length; j++) {
				Field[] fs = clazz.getDeclaredFields();
				for (Field f : fs) {
					annotations = f.getAnnotationsByType(ZAutoWrite.class);
					for (int k = 0; k < annotations.length; k++) {
						if (annotations[k].annotationType().equals(ZAutoWrite.class)) {
							config.addField(f.getName());
						}
					}
				}
			}
		}
	}
}
