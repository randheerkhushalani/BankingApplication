package com.example.bankingapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapplication.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}