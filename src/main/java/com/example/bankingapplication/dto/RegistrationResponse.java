package com.example.bankingapplication.dto;

import com.example.bankingapplication.model.Customer;

public class RegistrationResponse {
	private Customer customer;
	
	public RegistrationResponse(Customer customer) {
		super();
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
