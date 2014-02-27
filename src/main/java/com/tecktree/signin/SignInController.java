package com.tecktree.signin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignInController {
	
	protected Logger log = LoggerFactory.getLogger(SignInController.class);
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showSigninPage(WebRequest request, Model model){
		
		log.debug("Rendering signin page.");
		
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
    	
        if (connection != null) return "redirect:/";
		
		return "/signin/signin";
	}
	
}
