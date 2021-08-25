package com.example.bankingapplication.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
    private int txId;
    private final int srcAccountId;
    private final int destAccountId;
    @CreationTimestamp
    private final LocalDateTime timeStamp;
    private final BigDecimal amount;
    
    public Transaction( int srcAccountId, int destAccountId, LocalDateTime timeStamp,
			BigDecimal amount) {
		super();
		this.srcAccountId = srcAccountId;
		this.destAccountId = destAccountId;
		this.timeStamp = timeStamp;
		this.amount = amount;
	}
    
    
	public void setTxId(int txId) {
		this.txId = txId;
	}

	// getters
    public int getTxId() {
        return txId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

	public int getSrcAccountId() {
		return srcAccountId;
	}

	public int getDestAccountId() {
		return destAccountId;
	}
    
    
}
