package remeberme;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(scanBasePackages = "remeberme.**")
@MapperScan("remeberme.mapping")
@EnableAutoConfiguration
public class AppConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource()
	{
		return DataSourceBuilder.create().build();
	}
	@Bean
	@Autowired
	public PlatformTransactionManager getTransactionManager(DataSource datasource) {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(datasource);
		return tx;
	}
	
	@Bean
	@Autowired
	public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource datasource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	@Autowired
	public SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AppConfig.class, args);
	}
}
