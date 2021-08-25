package com.example.bankingapplication.exception;

public class BeneficiaryNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeneficiaryNotFoundException() {
    }

    public BeneficiaryNotFoundException(String msg) {
        super(msg);
    }
}