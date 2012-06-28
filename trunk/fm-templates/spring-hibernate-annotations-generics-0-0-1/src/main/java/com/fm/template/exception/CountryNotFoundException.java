package com.fm.template.exception;

@SuppressWarnings("serial")
public class CountryNotFoundException extends Exception {

	public CountryNotFoundException() {
		super();
	}

	public CountryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(Throwable cause) {
		super(cause);
	}

}
