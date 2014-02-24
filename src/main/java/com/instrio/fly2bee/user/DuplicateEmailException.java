package com.instrio.fly2bee.user;

import com.instrio.fly2bee.exception.AbstractMessageException;

@SuppressWarnings("serial")
public final class DuplicateEmailException extends AbstractMessageException {

	private final String email;
	
	public DuplicateEmailException(String email) {
		super("Duplicate Email");
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
}
