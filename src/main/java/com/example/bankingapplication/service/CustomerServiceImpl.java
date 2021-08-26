package com.example.bankingapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapplication.exception.CustomerNotFoundException;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerInfo(int custID) {
		return customerRepository.findById(custID).orElseThrow(() -> 
		new CustomerNotFoundException("Follwoing customer Id is not available" + custID));
	}

	@Override
	public void deleteCustomer(int custID) {
		if(!customerRepository.findById(custID).isPresent()) {
			throw new CustomerNotFoundException("Follwoing customer Id is not available" + custID);
		}
			customerRepository.deleteById(custID);
	}

	@Override
	public Boolean isAadharNoRegistered(String AadharNo) {
		return customerRepository.findByAadharNo(AadharNo).isPresent();
	}

}