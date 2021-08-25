package com.example.bankingapplication.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.servlet.Registration;
import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.bankingapplication.dto.RegistrationData;
import com.example.bankingapplication.dto.RegistrationResponse;
import com.example.bankingapplication.model.Account;
import com.example.bankingapplication.model.Beneficiary;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.model.UserLoginDetails;
import com.example.bankingapplication.service.AccountService;
import com.example.bankingapplication.service.BeneficiaryService;
import com.example.bankingapplication.service.CustomerService;
import com.example.bankingapplication.service.UserService;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {
	@Mock
	AccountService accountService;
	
	@Mock
	CustomerService customerService;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	RegistrationController registrationContoller;
	
	static Beneficiary beneficiary;
	static RegistrationData registrationData;
		
	
	  @BeforeAll public static void setUp() { 
		  registrationData = new RegistrationData();
		  registrationData.setAadharNo("556678901234");
		  registrationData.setAccountType("savings");
		  registrationData.setAddressLine1("234");
		  registrationData.setAddressLine2("lane no 04");
		  registrationData.setCity("Nagpur");
		  registrationData.setCountryCode("IND");
		  registrationData.setBirthDate(LocalDate.of(1996, 2, 11));
		  registrationData.setEmailAddress("rajatk@gmail.com");
		  registrationData.setFirstName("Rajat");
		  registrationData.setInitialDeposit(BigDecimal.valueOf(5000));
		  registrationData.setMobilePhone("983474579");
		  registrationData.setStateProvince("Maharashtra");
		  registrationData.setZipCode("440014");
	  }
	 
	
	@Test
	@DisplayName("Register User Test")
	public void RegistrationTest() {
		ResponseEntity<RegistrationResponse> response = registrationContoller.register(registrationData);
		verify(accountService).createAccount(any(Account.class));
		verify(customerService).createCustomer(any(Customer.class));
		verify(userService).addUserLoginDetails(any(UserLoginDetails.class));
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
}
