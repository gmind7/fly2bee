package com.instrio.fly2bee.config.database;

import java.util.Properties;

import javax.inject.Inject;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories("com.instrio")
public class JpaConfig {
	
	@Inject
	private Environment environment;
	
	@Inject
	private DataSourceConfig dataSourceConfig;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);  
		entityManager.setPackagesToScan(environment.getRequiredProperty("hibernate.packagesToScan"));
		entityManager.setMappingResources(environment.getRequiredProperty("hibernate.mappingResources"));
		entityManager.setDataSource(dataSourceConfig.dataSource());
		entityManager.setJpaProperties(additionalProperties());
		return entityManager;
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
	
	public Properties additionalProperties(){
		Properties properties = new Properties();
		// Generated SQL
		properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		// Logging
		properties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
		// charset
		properties.setProperty("hibernate.connection.CharSet", environment.getRequiredProperty("hibernate.connection.CharSet"));
		properties.setProperty("hibernate.connection.characterEncoding", environment.getRequiredProperty("hibernate.connection.characterEncoding"));
		properties.setProperty("hibernate.connection.useUnicode", environment.getRequiredProperty("hibernate.connection.useUnicode"));
		// Etc
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.enable_lazy_load_no_trans", environment.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
		properties.setProperty("hibernate.auto_close_session", environment.getRequiredProperty("hibernate.auto_close_session"));
		properties.setProperty("hibernate.cache.use_second_level_cache", environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
		properties.setProperty("hibernate.cache.use_query_cache", environment.getRequiredProperty("hibernate.cache.use_query_cache"));
		properties.setProperty("hibernate.generate_statistics", environment.getRequiredProperty("hibernate.generate_statistics"));
		properties.setProperty("net.sf.ehcache.configurationResourceName", environment.getRequiredProperty("hibernate.net.sf.ehcache.configurationResourceName"));
		properties.setProperty("hibernate.cache.region.factory_class", environment.getRequiredProperty("hibernate.cache.region.factory_class"));
		properties.setProperty("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		
		return properties;
	}
	
	
}
