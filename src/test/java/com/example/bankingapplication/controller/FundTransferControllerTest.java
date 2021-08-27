package com.example.bankingapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.bankingapplication.dto.TransferRequest;
import com.example.bankingapplication.model.Transaction;
import com.example.bankingapplication.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class FundTransferControllerTest {
	@Mock
	TransactionService transactionService;
	
	@InjectMocks
	FundTransferController fundTransferController;
	
	static Transaction transaction;
	static TransferRequest transferRequest;
		
	
	  @BeforeAll 
	  public static void setUp() { 
		  transferRequest = new TransferRequest(90001, 90002, BigDecimal.valueOf(500));
		  transaction = new Transaction(90001, 90002, LocalDateTime.now(), BigDecimal.valueOf(500));
	  }
	 
	
	@Test
	@DisplayName("Fund Transfer Test")
	public void FundTransferTest() {
		when(transactionService.transferFunds(transferRequest)).thenReturn(transaction);
		ResponseEntity<Transaction> response = fundTransferController.transferAmount(transferRequest);
		verify(transactionService).transferFunds(transferRequest);
		assertEquals(transaction, response.getBody());
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
}
