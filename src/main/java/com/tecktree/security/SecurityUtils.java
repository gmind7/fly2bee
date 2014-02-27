package com.tecktree.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tecktree.user.User;
import com.tecktree.user.UserDetails;

public class SecurityUtils {

    private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    public static void signInUser(User user) {
    	log.info("Logging in user: {}", user);

        UserDetails userDetails = UserDetails.getBuilder()
                .id(user.getId())
                .userId(user.getUserId()) 
                .name(user.getName())
                .userName(user.getUserName()) // userconnection userId
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .providerId(user.getProviderId())
                .build();
        log.debug("signInUser in principal: {}", userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("User: {} has been logged in.", userDetails);
    }
}
