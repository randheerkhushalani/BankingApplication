package com.example.bankingapplication.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.DecimalMin;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@TableGenerator(name="tab", initialValue=90000, allocationSize=50)
public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    private int accountId;
    private String type;
    @DecimalMin(value="500")
    private BigDecimal balance;
    @DecimalMin(value="500")
    private BigDecimal beginBalance;
    @CreationTimestamp
    private LocalDateTime beginBalanceTimeStamp;
    @OneToOne
    private Customer customer;
    
    public Account() {        
    }

    public Account(String type,
            BigDecimal balance,BigDecimal beginBalance,
            LocalDateTime beginBalanceTimeStamp) {
        this.type = type;
        this.balance = balance;
        this.beginBalance = beginBalance;
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }

    // getters
    public int getAccountId() {
        return accountId;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getBeginBalance() {
        return beginBalance;
    }

    public LocalDateTime getBeginBalanceTimeStamp() {
        return beginBalanceTimeStamp;
    }

    // setters
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setBeginBalance(BigDecimal beginBalance) {
        this.beginBalance = beginBalance;
    }

    public void setBeginBalanceTimeStamp(LocalDateTime beginBalanceTimeStamp) {
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}