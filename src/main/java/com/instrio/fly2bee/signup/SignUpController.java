package com.instrio.fly2bee.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.instrio.fly2bee.security.ProviderMedia;
import com.instrio.fly2bee.security.SecurityUtils;
import com.instrio.fly2bee.user.DuplicateUserIdException;
import com.instrio.fly2bee.user.User;

@Controller
public class SignUpController {

    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);

    private SignUpService service;

    @Autowired
    public SignUpController(SignUpService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignupPage(WebRequest request, Model model) {
    	
    	log.debug("Rendering signup page.");
    	
        Connection<?> connection = ProviderSignInUtils.getConnection(request);
    	
        if (connection == null) return "/signup/signup";
        
        User user = createRegistrationSignUp(connection);
        
         //If email address was already found from the database, render the form view.
        if (user == null) {
        	log.debug("An userId address was found from the database. Rendering form view.");
            return "/signup/signup";
        }

        log.debug("Registered user account with information: {}", user);

        //Logs the user in.
        SecurityUtils.signInUser(user);
        log.debug("User {} has been signed in");
        //If the user is signing in by using a social provider, this method call stores
        //the connection to the UserConnection table. Otherwise, this method does not
        //do anything.
        ProviderSignInUtils.handlePostSignUp(user.getId().toString(), request);
        
    	return "redirect:/";
    }
    
    private User createRegistrationSignUp(Connection<?> connection) {
    	
    	SignUp signUp = new SignUp();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            
            String userId = socialMediaProfile.getEmail();
            
            if(userId==null) userId = socialMediaProfile.getUsername();
            
            signUp.setUserId(userId);
            signUp.setEmail(socialMediaProfile.getEmail());
            signUp.setName(socialMediaProfile.getName());
            signUp.setUserName(socialMediaProfile.getUsername());
            signUp.setFirstName(socialMediaProfile.getFirstName());
            signUp.setLastName(socialMediaProfile.getLastName());

            ConnectionKey providerKey = connection.getKey();
            
            signUp.setProviderId(ProviderMedia.valueOf(providerKey.getProviderId()));
            
        }
        User registered = null;
        try {
            registered = service.registerNewUserAccount(signUp);
        }
        catch (DuplicateUserIdException ex) {
        	log.debug("An userId : {} exists.", signUp.getUserId());
        }

        return registered;
    }
    
}
