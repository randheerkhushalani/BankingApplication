package com.example.bankingapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bankingapplication.exception.AccountNotFoundException;
import com.example.bankingapplication.exception.InsufficientFundsException;
import com.example.bankingapplication.model.Account;
import com.example.bankingapplication.model.Customer;
import com.example.bankingapplication.repo.AccountsRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@InjectMocks
	private AccountServiceImpl accountServiceImpl;

	@Mock
	private AccountsRepository accountRepository;

	@Mock
	private Customer customer;

	static Account account;

	@BeforeAll
	public static void setUp() {
		account = new Account();
		account.setBalance(BigDecimal.valueOf(5000));
		account.setBeginBalance(BigDecimal.valueOf(5000));
		account.setBeginBalanceTimeStamp(LocalDateTime.now());
		account.setType("savings");
	}


	@Test
	public void createAccountTest() {
		when(accountRepository.save(account)).thenAnswer( i -> {
			Account account = i.getArgument(0);
			account.setAccountId(90001);
			return account;
		});
		Account result = accountServiceImpl.createAccount(account);
		verify(accountRepository).save(account);
		assertEquals(90001,result.getAccountId());
	}

	@Test
	public void getAccountInfoTest() throws Exception {

		when(accountRepository.findById(90001)).thenReturn(Optional.of(account));
		Account result = accountServiceImpl.getAccountInfo(90001);
		assertNotNull(result);
		assertEquals(account.getAccountId(), result.getAccountId());

	}

	@Test
	public void getAccountInfoNotAvailableTest() throws Exception {
		when(accountRepository.findById(90001)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, () -> {accountServiceImpl.getAccountInfo(90001);});
	}

	@Test
	public void deleteAccountTest(){
		when(accountRepository.findById(90001)).thenReturn(Optional.of(account));
		accountServiceImpl.deleteAccount(90001);
		verify(accountRepository).deleteById(90001);
	}

	@Test
	public void deletNotFoundAccountTest() {
		when(accountRepository.findById(90001)).thenReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, () -> {accountServiceImpl.deleteAccount(90001);});
	}
	
	@Test
	public void DepositAmountTest() {
		when(accountRepository.findById(90001)).thenReturn(Optional.of(account));
		accountServiceImpl.depositAmount(90001, BigDecimal.valueOf(500));
		verify(accountRepository).saveBalanceByAcctID(90001, BigDecimal.valueOf(500));
	}
	
	@Test
	public void withDrawAmountTest() {
		when(accountRepository.findById(90001)).thenReturn(Optional.of(account));
		when(accountRepository.getById(90001)).thenReturn(account);
		accountServiceImpl.withdrawAmount(90001, BigDecimal.valueOf(4000));
		verify(accountRepository).withdrawAmountByAcctID(90001, BigDecimal.valueOf(4000));
		
	}
	
	@Test
	@DisplayName("Withdraw Amount : Insufficient Balance")
	public void withDrawAmountInsufficientTest() {
		when(accountRepository.findById(90001)).thenReturn(Optional.of(account));
		when(accountRepository.getById(90001)).thenReturn(account);
		assertThrows(InsufficientFundsException.class, () -> {accountServiceImpl.withdrawAmount(90001, BigDecimal.valueOf(6000));});
	}
}
