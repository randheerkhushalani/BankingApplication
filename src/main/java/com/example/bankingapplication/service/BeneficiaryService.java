package com.example.bankingapplication.service;

import com.example.bankingapplication.model.Beneficiary;

public interface BeneficiaryService {

	Beneficiary createBeneficiary(Beneficiary beneficiary);

	Beneficiary getBeneficiaryInfo(int beneficiaryID);

	void deleteBeneficiary(int beneficiaryID);

}
