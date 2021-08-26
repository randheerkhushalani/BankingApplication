package com.example.bankingapplication.exception;

public class ExistingBeneFiciaryException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingBeneFiciaryException() {
    }

    public ExistingBeneFiciaryException(String msg) {
        super(msg);
    }
}