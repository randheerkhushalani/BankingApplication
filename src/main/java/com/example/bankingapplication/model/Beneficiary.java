package com.example.bankingapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Beneficiary {
	
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message="Name should not be blank")
	private String name;
	private String description;
	@NotBlank(message="IFSC code should not be blank.Please refer your passbook.")
	private String ifscCode;
	@NotBlank
	private String branch;
	@Min(value=9000,message="Account number should not be empty")
	private int  accNo;
	private String bankName;
	@NotNull(message="Customer id should not be empty")
	@Min(value = 101,message="customer Id should be more than 100")
	private int customerId;
	
	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Beneficiary(String name, String description, String ifscCode, String branch, int accNo, String bankName,
			int customerId) {
		super();
		this.name = name;
		this.description = description;
		this.ifscCode = ifscCode;
		this.branch = branch;
		this.accNo = accNo;
		this.bankName = bankName;
		this.customerId = customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomer(int customerId) {
		this.customerId = customerId;
	}
		
}
