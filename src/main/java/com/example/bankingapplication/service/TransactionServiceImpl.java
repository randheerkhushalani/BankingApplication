package com.example.bankingapplication.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankingapplication.model.Transaction;
import com.example.bankingapplication.repo.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction transferFunds(BigDecimal amount,
            int fromAccountId, int toAccountId) {
        accountService.withdrawAmount(fromAccountId, amount);
        accountService.depositAmount(toAccountId, amount);
        Transaction transaction = new Transaction(fromAccountId, toAccountId, LocalDateTime.now(),amount);
        return transactionRepository.save(transaction);
    }
}
