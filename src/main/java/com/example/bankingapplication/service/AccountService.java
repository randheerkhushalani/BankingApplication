package com.example.bankingapplication.service;

import java.math.BigDecimal;

import com.example.bankingapplication.model.Account;

public interface AccountService {

	Account createAccount(Account acct);

	Account getAccountInfo(int acctID);

	void deleteAccount(int acctID);

	BigDecimal getBalance(int acctID);

	void depositAmount(int acctID, BigDecimal amount);

	void withdrawAmount(int acctID, BigDecimal amount);
	
}
