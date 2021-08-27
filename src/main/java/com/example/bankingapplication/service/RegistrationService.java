package com.example.bankingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankingapplication.dto.RegistrationData;
import com.example.bankingapplication.dto.RegistrationResponse;
import com.example.bankingapplication.exception.ExistingCustomerException;
import com.example.bankingapplication.model.Account;
import com.example.bankingapplication.model.Address;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.model.UserLoginDetails;
import com.example.bankingapplication.util.PasswordGenerator;

@Service
@Transactional
public class RegistrationService {
	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;
	
	
 public  ResponseEntity<RegistrationResponse> registerUser(RegistrationData request) {
	 RegistrationResponse response;
	 if(customerService.isAadharNoRegistered(request.getAadharNo())) {
			throw new ExistingCustomerException("Customer is already registered with following aadhar number : " + request.getAadharNo());
		} else {
		Address address = new Address(request.getAddressLine1() + "," + request.getAddressLine2(),request.getCity(),
				request.getStateProvince(),request.getZipCode());
		Account account = new Account();
		account.setType(request.getAccountType());
		account.setBalance(request.getInitialDeposit());
		account.setBeginBalance(request.getInitialDeposit());
		UserLoginDetails userLoginDetails = new UserLoginDetails();
		userLoginDetails.setUserName(request.getEmailAddress());
		userLoginDetails.setPassword(PasswordGenerator.generateStrongPassword());
		userLoginDetails.setActive(true);
		userLoginDetails.setRoles("USER");
		Customer customer = new Customer(request.getLastName(),request.getFirstName(),request.getAadharNo(),
				address, request.getMobilePhone(), request.getEmailAddress());
		customer.setAccount(account);
		customer.setLogin(userLoginDetails);
		account.setCustomer(customer);
		userLoginDetails.setCustomer(customer);
		customerService.createCustomer(customer);
		accountService.createAccount(account);
		userService.addUserLoginDetails(userLoginDetails);
		response = new RegistrationResponse(customer);
		}
		return new ResponseEntity<RegistrationResponse>(response, HttpStatus.CREATED);
	} 
 }

