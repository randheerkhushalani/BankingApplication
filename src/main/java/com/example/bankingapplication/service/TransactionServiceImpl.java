package com.example.bankingapplication.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankingapplication.dto.TransferRequest;
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
	public Transaction transferFunds(TransferRequest request) {
		accountService.withdrawAmount(request.getSrcAccountId(), request.getAmount());
		accountService.depositAmount(request.getDestAccountId(), request.getAmount());
		Transaction transaction = new Transaction(request.getSrcAccountId(),
				request.getDestAccountId(), LocalDateTime.now(), request.getAmount());
		return transactionRepository.save(transaction);
	}
}
