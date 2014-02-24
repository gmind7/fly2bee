package com.instrio.fly2bee.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	log.debug("Loading user by userId: {}", userId);

        User user = repository.findOne(Long.parseLong(userId));
        log.debug("Found user: {}", user);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with userId: " + userId);
        }

        UserDetails principal = UserDetails.getBuilder()
                .id(user.getId())
                .userId(user.getUserId())
                .name(user.getName())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .providerId(user.getProviderId())
                .build();

        log.debug("Returning user details: {}", principal);

        return principal;
    }
}
