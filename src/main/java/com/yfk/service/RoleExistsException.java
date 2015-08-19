package com.yfk.service;


public class RoleExistsException extends Exception {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor for UserExistsException.
     *
     * @param message exception message
     */
    public RoleExistsException(final String message) {
        super(message);
    }
}
