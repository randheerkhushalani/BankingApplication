package com.example.bankingapplication.exception;
public class TxNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TxNotFoundException() {
    }

    public TxNotFoundException(String msg) {
        super(msg);
    }
}