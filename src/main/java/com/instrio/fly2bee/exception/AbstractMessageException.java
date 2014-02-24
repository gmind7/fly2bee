package com.instrio.fly2bee.exception;

@SuppressWarnings("serial")
public abstract class AbstractMessageException extends Exception {

	public AbstractMessageException(String message) {
		super(message);
	}

	public AbstractMessageException(String message, Throwable cause) {
		super(message, cause);
	}
	
}