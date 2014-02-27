package com.tecktree.signup;

import com.tecktree.user.DuplicateUserIdException;
import com.tecktree.user.User;

public interface SignUpService {

    public User registerNewUserAccount(SignUp userAccountData) throws DuplicateUserIdException;
}
