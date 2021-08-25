package com.example.bankingapplication.service;

import com.example.bankingapplication.model.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer getCustomerInfo(int custID);

	void deleteCustomer(int custID);

}
