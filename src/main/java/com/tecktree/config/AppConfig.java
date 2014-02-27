package com.tecktree.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.tecktree", excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class AppConfig {
	
	public AppConfig(){
		StringBuffer sb = new StringBuffer("\n");
		sb.append(" _____          _      _____                        \n");
		sb.append("/__   \\___  ___| | __ /__   \\_ __ ___  ___        \n");
		sb.append("  / /\\/ _ \\/ __| |/ /   / /\\/ '__/ _ \\/ _ \\    \n");
		sb.append(" / / |  __/ (__|   <   / /  | | |  __/  __/         \n");
		sb.append(" \\/   \\___|\\___|_|\\_\\  \\/   |_|  \\___|\\___| \n");
		sb.append(":: Welcome to the TeckTree Studio___________        \n");
		sb.append("\n");
		System.out.print(sb.toString());
	}
	
	@Inject
	private Environment environment;
	
	@Bean(name="messageSource")
	public MessageSource messageSource() {
		List<String> fileList = new ArrayList<String>();
        fileList.add("classpath:message/message");
        fileList.add("classpath:message/validation");
        
        String[] files = (String[])fileList.toArray(new String[fileList.size()]);
        
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(files);        
        messageSource.setDefaultEncoding("UTF-8");
        //messageSource.setAlwaysUseMessageFormat(true);
        if (environment.acceptsProfiles("loc")) {
			messageSource.setCacheSeconds(0);
		}
        return messageSource;
	}
	
	@Bean(name="messageSourceAccessor")
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource());
	}
		
}