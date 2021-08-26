package com.example.bankingapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapplication.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByAadharNo(String AadharNo);
}