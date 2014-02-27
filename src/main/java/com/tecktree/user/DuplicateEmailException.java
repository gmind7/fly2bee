package com.tecktree.user;

import com.tecktree.exception.AbstractMessageException;

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
