package com.example.bankingapplication.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bankingapplication.model.Beneficiary;
import com.example.bankingapplication.service.BeneficiaryService;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryControllerTest {

 
	//given or context
	//on a event
	//what is the outcome
	
	@Mock
	BeneficiaryService beneficiaryService;
	
	@InjectMocks
	BeneficiaryContoller beneficiaryContoller;
	
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
	@DisplayName("Add Beneficiary: Positive Scenario")
	public void addBeneficiaryTest() {
		beneficiaryContoller.addBeneficiary(beneficiary);
		verify(beneficiaryService).createBeneficiary(beneficiary);
	}

}