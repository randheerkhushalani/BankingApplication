package com.example.bankingapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapplication.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

}
