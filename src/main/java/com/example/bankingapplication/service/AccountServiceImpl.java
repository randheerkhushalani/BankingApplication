package com.example.bankingapplication.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapplication.exception.AccountNotFoundException;
import com.example.bankingapplication.exception.InsufficientFundsException;
import com.example.bankingapplication.model.Account;
import com.example.bankingapplication.repo.AccountsRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountsRepository accountRepository;
	
	@Override
	public Account createAccount(Account acct) {
		return accountRepository.save(acct);
	}
	
	@Override
	public Account getAccountInfo(int acctID) {
		return accountRepository.findById(acctID)
				.orElseThrow(() -> new AccountNotFoundException("Following Account number not available:" + acctID));
	}
	
	@Override
	public void deleteAccount(int acctID) {
		if(!accountRepository.findById(acctID).isPresent()) {
			throw new AccountNotFoundException("Following Account number not available:" + acctID);
		}
		accountRepository.deleteById(acctID);
	}
	
	@Override
	public BigDecimal getBalance(int acctID) {
		if(!accountRepository.findById(acctID).isPresent()) {
			throw new AccountNotFoundException("Following Account number not available:" + acctID);
		}
		return accountRepository.findBalanceByAccountID(acctID);
	}
	
	@Override
	public void depositAmount(int acctID, BigDecimal amount) {
		if(!accountRepository.findById(acctID).isPresent()) {
			throw new AccountNotFoundException("Following Account number not available:" + acctID);
		}
		accountRepository.saveBalanceByAcctID(acctID, amount);
	}
	
	@Override
	public void withdrawAmount(int acctID, BigDecimal amount) {
		if(!accountRepository.findById(acctID).isPresent()) {
			throw new AccountNotFoundException("Following Account number not available:" + acctID);
		}
		if(accountRepository.getById(acctID).getBalance()
				.subtract(BigDecimal.valueOf(500)).compareTo(amount) == -1) {
			throw new InsufficientFundsException("Minimum amount required for transaction is not available in acct:" + acctID);
			
		}
		accountRepository.withdrawAmountByAcctID(acctID, amount);
	}
}