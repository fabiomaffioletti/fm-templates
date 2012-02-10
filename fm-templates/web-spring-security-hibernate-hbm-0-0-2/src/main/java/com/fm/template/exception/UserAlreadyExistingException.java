package com.fm.template.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistingException extends Exception {

	public UserAlreadyExistingException() {
		super();
	}

	public UserAlreadyExistingException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistingException(String message) {
		super(message);
	}

	public UserAlreadyExistingException(Throwable cause) {
		super(cause);
	}

}
