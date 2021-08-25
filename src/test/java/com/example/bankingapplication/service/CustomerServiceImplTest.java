package com.example.bankingapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bankingapplication.exception.CustomerNotFoundException;
import com.example.bankingapplication.model.Address;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.model.UserLoginDetails;
import com.example.bankingapplication.repo.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerRepository customerRepository;

	static Customer customer;

	static Address address;

	static UserLoginDetails loginDetails;

	@BeforeAll
	public static void setUp() {
		customer = new Customer();
		customer.setAadharNo("57000945678902");
		customer.setEmail("rajatk@gmail.com");
		customer.setFirstName("Rajat");
		customer.setLastName("Khushalani");
		customer.setPhone("9834705279");
		customer.setAddress(address);
		customer.setLogin(loginDetails);

		address = new Address();
		address.setCity("Nagpur");
		address.setState("Maharashtra");
		address.setStreet("626,04");
		address.setZip("440014");

		loginDetails = new UserLoginDetails();
		loginDetails.setUserId(1);
		loginDetails.setPassword("asdfg");
		loginDetails.setUserName("rajatk");
		loginDetails.setCustomer(customer);
		loginDetails.setActive(true);
	}


	@Test
	public void createCustomerTest() {
		when(customerRepository.save(customer)).thenAnswer( i -> {
			Customer customer = i.getArgument(0);
			customer.setCustomerId(101);
			return customer;
		});
		Customer result = customerServiceImpl.createCustomer(customer);
		verify(customerRepository).save(customer);
		assertEquals(101,result.getCustomerId());
	}

	@Test
	public void getCustomerInfoTest() throws Exception {

		when(customerRepository.findById(101)).thenReturn(Optional.of(customer));
		Customer result = customerServiceImpl.getCustomerInfo(101);
		assertNotNull(result);
		assertEquals(customer.getCustomerId(), result.getCustomerId());

	}

	@Test
	public void getCustomerInfoNotAvailableTest() throws Exception {
		when(customerRepository.findById(101)).thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, () -> {customerServiceImpl.getCustomerInfo(101);});
	}

	@Test
	public void deleteCustomerTest(){
		when(customerRepository.findById(101)).thenReturn(Optional.of(customer));
		customerServiceImpl.deleteCustomer(101);
		verify(customerRepository).deleteById(101);
	}

	@Test
	public void deletNotAvailableCustomerTest() {
		when(customerRepository.findById(101)).thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, () -> {customerServiceImpl.deleteCustomer(101);});
	}
}
