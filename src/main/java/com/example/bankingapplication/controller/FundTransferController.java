package com.example.bankingapplication.controller;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.dto.TransferRequest;
import com.example.bankingapplication.model.Transaction;
import com.example.bankingapplication.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class FundTransferController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/fundtransfer")
	public ResponseEntity<Transaction> transferAmount(@Valid @RequestBody TransferRequest request) {
		Transaction transaction = transactionService.transferFunds(request);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
}
