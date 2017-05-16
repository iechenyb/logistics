package com.genpact.logistics.common.config;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableCaching
public class BaseConfig {
	Log log = LogFactory.getLog(BaseConfig.class);
	@Bean
	public ServletRegistrationBean druidServlet(@Value("${druid.allow}") String allow) {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		
		//设置数据库连接池只能本级访问提升安全性
		//http://localhost:8080/druid/index.html
		HashMap<String, String> initParameters = new HashMap<String,String>();
		initParameters.put("allow", allow);
		bean.setInitParameters(initParameters);
		log.info("druidServlet");
		return bean;
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean(@Value("${druid.exclusions}") String exclusions) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", exclusions);
		log.info("filterRegistrationBean");
		return filterRegistrationBean;
	}

	/*@Bean
	public DruidDataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
										   @Value("${spring.datasource.url}") String url,
										   @Value("${spring.datasource.username}") String username,
										   @Value("${spring.datasource.password}") String password,
										   @Value("${spring.datasource.filters}") String filters,
										   @Value("${spring.datasource.maxActive}") int maxActive,
										   @Value("${spring.datasource.initialSize}") int initialSize,
										   @Value("${spring.datasource.maxWait}") long maxWait,
										   @Value("${spring.datasource.minIdle}") int minIdle,
										   @Value("${spring.datasource.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMillis,
										   @Value("${spring.datasource.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis,
										   @Value("${spring.datasource.validationQuery}") String validationQuery,
										   @Value("${spring.datasource.testWhileIdle}") boolean testWhileIdle,
										   @Value("${spring.datasource.testOnBorrow}") boolean testOnBorrow,
										   @Value("${spring.datasource.testOnReturn}") boolean testOnReturn,
										   @Value("${spring.datasource.poolPreparedStatements}") boolean poolPreparedStatements,
										   @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}") int maxPoolPreparedStatementPerConnectionSize
										   ) throws IOException, SQLException {
		DruidDataSource dataSource = new DruidDataSource();
//		Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setPassword(password);
		dataSource.setUsername(username);

		// 下面的内容可以提取到application.properties 中
		dataSource.setFilters(filters);
		dataSource.setMaxActive(maxActive);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxWait(maxWait);
		dataSource.setMinIdle(minIdle);

		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setPoolPreparedStatements(poolPreparedStatements);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

		return dataSource;
	}*/




	

}
