package com.instrio.fly2bee.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplateErrorHandler;

@Configuration
public class HttpConfig {
	
	@Bean(name="httpClientFactory")
	public HttpComponentsClientHttpRequestFactory httpClientFactory() {
		HttpComponentsClientHttpRequestFactory httpClientFactory = new HttpComponentsClientHttpRequestFactory();
		httpClientFactory.setConnectTimeout(3000);
		httpClientFactory.setReadTimeout(5000);
		return httpClientFactory;
	}
	
	@Bean(name="restTemplate")
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(httpClientFactory());
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());
		List<HttpMessageConverter<?>> converters =new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
	
}
