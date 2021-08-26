package com.example.bankingapplication.exception;

public class ExistingCustomerException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingCustomerException() {
    }

    public ExistingCustomerException(String msg) {
        super(msg);
    }
}