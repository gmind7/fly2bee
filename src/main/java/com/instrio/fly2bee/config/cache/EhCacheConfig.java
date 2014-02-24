package com.instrio.fly2bee.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EhCacheConfig {

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactory() {
	    EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
	    String configLocation = getEhcacheConfigResource()==null ? "META-INF/ehcache.xml" : getEhcacheConfigResource();
	    bean.setConfigLocation(new ClassPathResource(configLocation));
	    bean.setShared(true);
	    return bean;
	}
	
    public EhCacheCacheManager ehCacheManager() {
    	EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    	ehCacheCacheManager.setCacheManager(ehCacheManagerFactory().getObject());
        return ehCacheCacheManager;
    }
	
    @Bean
	public EhCacheCacheManager cacheManager() {
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    	ehCacheCacheManager.setCacheManager(this.ehCacheManagerFactory().getObject());
        return ehCacheCacheManager;
	}

	public String getEhcacheConfigResource() {
		return "META-INF/ehcache.xml";
	}
	
}