package com.example.bankingapplication.service;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.example.bankingapplication.dto.TransferRequest;
import com.example.bankingapplication.model.Transaction;

public interface TransactionService {
	Transaction transferFunds(TransferRequest request);
}
