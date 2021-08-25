package com.example.bankingapplication.repo;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.bankingapplication.model.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
	
	@Query("select balance from Account where account_id = ?1")
	BigDecimal findBalanceByAccountID(int acctID);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance+?2 where account_id=?1")
	public void saveBalanceByAcctID(int acctID, BigDecimal amount);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance-?2 where account_id=?1")
	public void withdrawAmountByAcctID(int acctID, BigDecimal amount);
}
