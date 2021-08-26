package com.example.bankingapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.dto.RegistrationData;
import com.example.bankingapplication.dto.RegistrationResponse;
import com.example.bankingapplication.exception.ExistingCustomerException;
import com.example.bankingapplication.model.Account;
import com.example.bankingapplication.model.Address;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.model.UserLoginDetails;
import com.example.bankingapplication.service.AccountService;
import com.example.bankingapplication.service.CustomerService;
import com.example.bankingapplication.service.UserService;
import com.example.bankingapplication.util.PasswordGenerator;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationData request) {
		if(customerService.isAadharNoRegistered(request.getAadharNo())) {
			throw new ExistingCustomerException("Customer is already registered with following aadhar number : " + request.getAadharNo());
		}
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
		customer.setLogin(userLoginDetails);
		customer.setAccount(account);
		account.setCustomer(customer);
		userLoginDetails.setCustomer(customer);
		accountService.createAccount(account);
		userService.addUserLoginDetails(userLoginDetails);
		customerService.createCustomer(customer);
		RegistrationResponse response = new RegistrationResponse(customer);
		return new ResponseEntity<RegistrationResponse>(response, HttpStatus.CREATED);
	}
}
