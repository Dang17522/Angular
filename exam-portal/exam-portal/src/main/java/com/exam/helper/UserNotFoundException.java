package com.exam.helper;

public class UserNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("user with this username not found in DB !!");
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
