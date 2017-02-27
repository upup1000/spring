package com.leader.lesson6.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.leader.lesson6.aop.aspect.MyJdbcTemplate;

/**
 * @author zss
 */
@Configuration
@ComponentScan("com.leader.lesson6")
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy(exposeProxy = true)
@Configurable
public class MySpringConfig {
    public MySpringConfig(){
        System.out.println("MySpringConfig.MySpringConfig");
    }
    @Value(value = "${conn.url}")
    private String connectionUrl;

    @Value(value = "${conn.username}")
    private String username;

    @Value(value = "${conn.password}")
    private String password;

    @Value(value = "${conn.driver}")
    private String driverName;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(connectionUrl,username,password);
        dataSource.setDriverClassName(driverName);
        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new MyJdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
