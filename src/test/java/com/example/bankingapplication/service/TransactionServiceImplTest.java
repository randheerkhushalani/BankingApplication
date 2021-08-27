package com.example.bankingapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bankingapplication.dto.TransferRequest;
import com.example.bankingapplication.model.Transaction;
import com.example.bankingapplication.model.UserLoginDetails;
import com.example.bankingapplication.repo.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;

	@Mock
	private TransactionRepository transactionRepository;
	
	@Mock
	private AccountService accountService;

	static Transaction transaction;
	
	static TransferRequest transferRequest;

	@BeforeAll
	public static void setUp() {
		  transferRequest = new TransferRequest(90001, 90002, BigDecimal.valueOf(500));
		transaction = new Transaction(90001, 90002, LocalDateTime.now(), BigDecimal.valueOf(500));
	}


	@Test
	public void transferFundsTest() {
		when(transactionRepository.save(any(Transaction.class))).thenAnswer( i -> {
			Transaction transaction = i.getArgument(0);
			transaction.setTxId(1);
			return transaction;
		});
		Transaction result = transactionServiceImpl.transferFunds(transferRequest);
		verify(accountService).withdrawAmount(90001, BigDecimal.valueOf(500));
		verify(accountService).depositAmount(90002, BigDecimal.valueOf(500));
		assertEquals(1,result.getTxId());
	}
}
