package com.instrio.fly2bee.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instrio.fly2bee.user.DuplicateUserIdException;
import com.instrio.fly2bee.user.User;
import com.instrio.fly2bee.user.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    private static final Logger log = LoggerFactory.getLogger(SignUpServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    private UserRepository repository;

    @Autowired
    public SignUpServiceImpl(PasswordEncoder passwordEncoder, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(SignUp signUp) throws DuplicateUserIdException {
    	
    	log.debug("Registering new user account with information: {}", signUp);

        if (userIdExist(signUp.getUserId())) {
        	log.debug("UserId: {} exists. Throwing exception.", signUp.getUserId());
            throw new DuplicateUserIdException("The UserId : " + signUp.getUserId() + " is already in use.");
        }

        log.debug("UserId: {} does not exist. Continuing registration.", signUp.getUserId());

        String encodedPassword = encodePassword(signUp);

        User.Builder user = User.getBuilder()
        		.userId(signUp.getUserId())
                .email(signUp.getEmail())
                .name(signUp.getName())
                .userName(signUp.getUserName())
                .firstName(signUp.getFirstName())
                .lastName(signUp.getLastName())
                .password(encodedPassword);

        if (signUp.isSocialSignIn()) {
            user.providerId(signUp.getProviderId());
        }

        User registered = user.build();

        log.debug("Persisting new user with information: {}", registered);

        return repository.save(registered);
    }

    private boolean userIdExist(String userId) {
    	log.debug("Checking if userId {} is already found from the database.", userId);

        User user = repository.findByUserId(userId);

        if (user != null) {
            log.debug("User account: {} found with userId: {}. Returning true.", user, userId);
            return true;
        }

        log.debug("No user account found with userId: {}. Returning false.", userId);

        return false;
    }

    private String encodePassword(SignUp signUp) {
        String encodedPassword = null;

        if (signUp.isNormalRegistration()) {
        	log.debug("Registration is normal registration. Encoding password.");
            encodedPassword = passwordEncoder.encode(signUp.getPassword());
        }

        return encodedPassword;
    }
    
}
