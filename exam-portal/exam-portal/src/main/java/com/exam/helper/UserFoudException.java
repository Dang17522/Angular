package com.exam.helper;

public class UserFoudException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserFoudException() {
		super("user with this username is already there in DB !! try with another one");
	}

	public UserFoudException(String msg) {
		super(msg);
	}

}
