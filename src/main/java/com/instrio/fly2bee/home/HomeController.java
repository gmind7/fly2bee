package com.instrio.fly2bee.home;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	protected Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	Environment env;
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String home(){
		log.debug("welcome to the fly2bee homeController.............");		
		return "/home/home";
	}
	
	@RequestMapping(value = {"/home/location"}, method = RequestMethod.GET)
	public String location(){
		log.debug("welcome to the fly2bee homeController.............");		
		return "/home/location";
	}
	
}
