package com.example.bankingapplication.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferRequest {
	
	@Min(value=90001,message="Account number should start with 90001")
	private int srcAccountId;
	@Min(value=90001,message="Account number should start with 90001")
	private int destAccountId;
	@NotNull
	@Max(value = 20000,message="Maximum tarnsaction limit is 20,000 INR")
	private BigDecimal amount;

	public TransferRequest(int srcAccountId, int destAccountId,
			@NotNull @Max(value = 20000, message = "Maximum tarnsaction limit is 20,000 INR") @NotNull @Max(value = 20000, message = "Maximum tarnsaction limit is 20,000 INR") BigDecimal amount) {
		super();
		this.srcAccountId = srcAccountId;
		this.destAccountId = destAccountId;
		this.amount = amount;
	}

	public TransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSrcAccountId() {
		return srcAccountId;
	}
	public void setSrcAccountId(int srcAccountId) {
		this.srcAccountId = srcAccountId;
	}
	public int getDestAccountId() {
		return destAccountId;
	}
	public void setDestAccountId(int destAccountId) {
		this.destAccountId = destAccountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(@NotNull @Max(value = 20000, message = "Maximum tarnsaction limit is 20,000 INR") BigDecimal amount) {
		this.amount = amount;
	}

}
