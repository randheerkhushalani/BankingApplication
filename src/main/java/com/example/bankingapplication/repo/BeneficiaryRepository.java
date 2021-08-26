package com.example.bankingapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapplication.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
	Optional<Beneficiary> findByAccNo(int accNo);
}
