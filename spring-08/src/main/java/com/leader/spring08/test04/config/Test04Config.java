package com.leader.spring08.test04.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.leader.spring08.test04.exception.TestException1;

@Configurable
@ComponentScan("com.leader.spring08.test04")
@ImportResource("classpath:dataSource.xml")
@EnableAspectJAutoProxy
public class Test04Config {
	
	/**
	 * 实现替代xml配置方式的Aop方式 这样可以集中管理配置需要AOP代理的方法
	 * @param live_datasource
	 * @return
	 */
	@Bean
	public AspectJExpressionPointcutAdvisor getAspectJExpressionPointcutAdvisor(DataSource live_datasource) {
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setAdvice(getTransactionInterceptor(live_datasource));
		advisor.setExpression("execution(* com.leader.spring08.test04.service.*.*(..))");
		return advisor;
	}
	/**
	 * 实现 取代xml的替代的 事务方式 这样可以集中管理配置需要开始事务的方法
	 * 前提是使用事务的类必须在execution(* com.leader.spring08.test04.service.*.*(..)) 这个表达式中包含
	 * @return
	 */
	@Bean
	public NameMatchTransactionAttributeSource getNameMatchTransactionAttributeSource() {
		NameMatchTransactionAttributeSource nta = new NameMatchTransactionAttributeSource();
		RuleBasedTransactionAttribute rbt = new RuleBasedTransactionAttribute();
		rbt.setReadOnly(true);
		List<RollbackRuleAttribute> norollbacks = Collections
				.singletonList(new RollbackRuleAttribute(TestException1.class));
		RuleBasedTransactionAttribute rbt1 = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED, norollbacks);
		Map<String, TransactionAttribute> maps = new HashMap();
		maps.put("add*", rbt);
		maps.put("get*", rbt1);
		nta.setNameMap(maps);
		return nta;
	}
	@Bean
	public TransactionInterceptor getTransactionInterceptor(DataSource live_datasource) {
		return new TransactionInterceptor(getTransactionManager(live_datasource),
				getNameMatchTransactionAttributeSource());
	}
	@Bean
	public PlatformTransactionManager getTransactionManager(DataSource live_datasource) {
		DataSourceTransactionManager txMan = new DataSourceTransactionManager();
		txMan.setDataSource(live_datasource);
		System.out.println("open PlatformTransactionManager");
		return txMan;
	}
	
}
