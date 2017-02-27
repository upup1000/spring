package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 组件 标志
 * @author zss
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)// 类、接口（包括注释类型）或枚举声明
public @interface ZComponent {
	String name() default "";
}
