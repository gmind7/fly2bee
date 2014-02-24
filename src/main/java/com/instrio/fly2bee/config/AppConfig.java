package com.instrio.fly2bee.config;

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
@ComponentScan(basePackages = "com.instrio", excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class AppConfig {
	
	public AppConfig(){
		StringBuffer sb = new StringBuffer("\n");
		sb.append("  _____           _        _         __ _             _ _               \n");
		sb.append("  \\_   \\_ __  ___| |_ _ __(_) ___   / _\\ |_ _   _  __| (_) ___       \n");
		sb.append("   / /\\/ '_ \\/ __| __| '__| |/ _ \\  \\ \\| __| | | |/ _` | |/ _ \\   \n");
		sb.append("/\\/ /_ | | | \\__ \\ |_| |  | | (_) | _\\ \\ |_| |_| | (_| | | (_) |   \n");
		sb.append("\\____/ |_| |_|___/\\__|_|  |_|\\___/  \\__/\\__|\\__,_|\\__,_|_|\\___/ \n");
		sb.append(":: Welcome to the Instrio Studio________________________________        \n");
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