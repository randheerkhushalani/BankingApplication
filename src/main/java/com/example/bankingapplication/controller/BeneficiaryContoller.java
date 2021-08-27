package com.example.bankingapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.exception.ExistingBeneFiciaryException;
import com.example.bankingapplication.model.Beneficiary;
import com.example.bankingapplication.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryContoller {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@PostMapping
	public ResponseEntity<Beneficiary> addBeneficiary(@Valid @RequestBody Beneficiary request) {
		if(beneficiaryService.isBeneficiaryAdded(request.getAccNo())) {
			throw new ExistingBeneFiciaryException("Beneficiary with following account no is already added:" + request.getAccNo());
		}
		Beneficiary response = beneficiaryService.createBeneficiary(request);
		return new ResponseEntity<Beneficiary>(response, HttpStatus.CREATED);
	}

}
