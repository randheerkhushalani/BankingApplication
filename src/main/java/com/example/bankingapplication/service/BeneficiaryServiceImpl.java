package com.example.bankingapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankingapplication.exception.BeneficiaryNotFoundException;
import com.example.bankingapplication.exception.CustomerNotFoundException;
import com.example.bankingapplication.model.Beneficiary;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.repo.BeneficiaryRepository;
import com.example.bankingapplication.repo.CustomerRepository;

@Service
@Transactional
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Beneficiary createBeneficiary(Beneficiary beneficiary) {
		Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
		addBeneficiaryToCustomer(savedBeneficiary);
		return savedBeneficiary;
	}

	private void addBeneficiaryToCustomer(Beneficiary savedBeneficiary) {
		Customer customer = customerRepository.findById(savedBeneficiary.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("No customer found with following customer id: "+ savedBeneficiary.getCustomerId()));
		customer.addBeneficiary(savedBeneficiary);
		customerRepository.save(customer);
	}

	@Override
	public Beneficiary getBeneficiaryInfo(int beneficiaryId) {
		return beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> 
		new BeneficiaryNotFoundException("Follwoing beneficiary Id is not available" + beneficiaryId));
	}

	@Override
	public void deleteBeneficiary(int beneficiaryId) {
		if(!beneficiaryRepository.findById(beneficiaryId).isPresent()) {
			throw new BeneficiaryNotFoundException("Follwoing beneficiary Id is not available" + beneficiaryId);
		}
		beneficiaryRepository.deleteById(beneficiaryId);
	}

	@Override
	public Boolean isBeneficiaryAdded(int accNo) {
		return beneficiaryRepository.findByAccNo(accNo).isPresent();
	}

}