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

import com.example.bankingapplication.exception.BeneficiaryNotFoundException;
import com.example.bankingapplication.model.Beneficiary;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.repo.BeneficiaryRepository;
import com.example.bankingapplication.repo.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceImplTest {

	@InjectMocks
	private BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Mock
	private BeneficiaryRepository beneficiaryRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private Customer customer;

	static Beneficiary beneficiary;

	@BeforeAll
	public static void setUp() {
		beneficiary = new Beneficiary();
		beneficiary.setAccNo(90001);
		beneficiary.setBankName("AXIS");
		beneficiary.setBranch("Jaripatka");
		beneficiary.setCustomer(2);
		beneficiary.setIfscCode("IBN1006");
		beneficiary.setName("Rajesh");
		beneficiary.setDescription("Brother");
	}


	@Test
	public void createBeneficiaryTest() {
		when(beneficiaryRepository.save(beneficiary)).thenAnswer( i -> {
			Beneficiary beneficiary = i.getArgument(0);
			beneficiary.setId(1);
			return beneficiary;
		});
		when(customerRepository.findById(2)).thenReturn(Optional.of(customer));
		Beneficiary result = beneficiaryServiceImpl.createBeneficiary(beneficiary);
		verify(customer).addBeneficiary(beneficiary);
		verify(customerRepository).save(customer);
		assertEquals(1,result.getId());
	}

	@Test
	public void getBeneficiaryInfoTest() throws Exception {

		when(beneficiaryRepository.findById(1)).thenReturn(Optional.of(beneficiary));
		Beneficiary result = beneficiaryServiceImpl.getBeneficiaryInfo(1);
		assertNotNull(result);
		assertEquals(beneficiary.getName(), result.getName());

	}

	@Test
	public void getBeneficiaryInfoNotAvailableTest() throws Exception {
		when(beneficiaryRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(BeneficiaryNotFoundException.class, () -> {beneficiaryServiceImpl.getBeneficiaryInfo(1);});
	}

	@Test
	public void deleteBeneficiaryTest(){
		when(beneficiaryRepository.findById(1)).thenReturn(Optional.of(beneficiary));
		beneficiaryServiceImpl.deleteBeneficiary(1);
		verify(beneficiaryRepository).deleteById(1);
	}

	@Test
	public void deletNotAvailableBeneficiaryTest() {
		when(beneficiaryRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(BeneficiaryNotFoundException.class, () -> {beneficiaryServiceImpl.deleteBeneficiary(1);});
	}
}
