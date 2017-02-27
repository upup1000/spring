package com.leader.spring052;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
/**
 * 检测实现超过两个接口的组件
 * @author zss
 */
@Component
public class InterfaceCheckBeanPostProcessor implements BeanPostProcessor{
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?>[] interfaces=bean.getClass().getInterfaces();
		if(interfaces.length>=2)
		{
			System.out.println(bean.getClass().getName()+" implements "+interfaces.length+" 个 接口");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
