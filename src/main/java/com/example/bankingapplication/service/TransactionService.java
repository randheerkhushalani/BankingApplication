package com.example.bankingapplication.service;

import java.math.BigDecimal;

import com.example.bankingapplication.model.Transaction;

public interface TransactionService {
	 Transaction transferFunds(BigDecimal amount,
	            int fromAccountId, int toAccountId);
}
