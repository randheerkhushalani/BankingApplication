package com.example.bankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.model.Beneficiary;
import com.example.bankingapplication.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryContoller {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@PostMapping
	public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary request) {
		Beneficiary response = beneficiaryService.createBeneficiary(request);
		return new ResponseEntity<Beneficiary>(response, HttpStatus.CREATED);
	}

}
