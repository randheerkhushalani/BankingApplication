package com.example.bankingapplication.controller;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.model.Transaction;
import com.example.bankingapplication.service.TransactionService;

@RestController
@Validated
public class FundTransferController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/accounts/{acctID}/transfer/{destAcctID}/{amount}")
	public ResponseEntity<Transaction> transferAmount(@NotNull @PathVariable Integer acctID, @NotNull @PathVariable Integer destAcctID, @DecimalMin(value="200")@PathVariable BigDecimal amount) {
		Transaction transaction = transactionService.transferFunds(amount,acctID,destAcctID);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
}
