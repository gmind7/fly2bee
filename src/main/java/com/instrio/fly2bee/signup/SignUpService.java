package com.instrio.fly2bee.signup;

import com.instrio.fly2bee.user.DuplicateUserIdException;
import com.instrio.fly2bee.user.User;

public interface SignUpService {

    public User registerNewUserAccount(SignUp userAccountData) throws DuplicateUserIdException;
}
