package com.instrio.fly2bee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class PropertyConfig {

	@Configuration
	@PropertySource("classpath:environment/application.properties")
	static class defaultPropertyConfig {
	}
	
	@Configuration
	@PropertySource("classpath:theme/theme-default.properties")
	static class themePropertyConfig {
	}
	
	@Configuration
	@Profile("loc")
	@PropertySource("classpath:environment/database_loc.properties")
	static class databaseLocalPropertyConfig {
	}
	
	@Configuration
	@Profile("aws")
	@PropertySource("classpath:environment/database_aws.properties")
	static class databaseAWSPropertyConfig {
	}
	
}
