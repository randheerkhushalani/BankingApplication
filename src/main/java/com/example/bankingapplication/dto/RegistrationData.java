package com.example.bankingapplication.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RegistrationData {
	
	@Email(message="please enter valid email address")
    String EmailAddress;
    String AccountType;
    String firstName;
    String lastName;
    String aadharNo;
    @Past(message = "Birthdate cannot be a present or future date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    @Size(min = 10, max=12, message = "mobile number should be of 10 0r 12 digits")
    String MobilePhone;
    String AddressLine1;
    String AddressLine2;
    String City;
    String ZipCode;
    String StateProvince;
    String CountryCode;
    @DecimalMin(value = "500")
    BigDecimal initialDeposit;
    
    
    
	public RegistrationData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationData(String emailAddress, String accountType, String firstName, String lastName, String aadharNo,
			LocalDate birthDate, String mobilePhone, String addressLine1, String addressLine2, String city, String zipCode,
			String stateProvince, String countryCode, BigDecimal initialDeposit) {
		super();
		EmailAddress = emailAddress;
		AccountType = accountType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.aadharNo = aadharNo;
		this.birthDate = birthDate;
		MobilePhone = mobilePhone;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
		City = city;
		ZipCode = zipCode;
		StateProvince = stateProvince;
		CountryCode = countryCode;
		this.initialDeposit = initialDeposit;
	}
	
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public String getAddressLine1() {
		return AddressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return AddressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getStateProvince() {
		return StateProvince;
	}
	public void setStateProvince(String stateProvince) {
		StateProvince = stateProvince;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public BigDecimal getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(BigDecimal initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	@Override
	public String toString() {
		return "RegistrationData [EmailAddress=" + EmailAddress + ", AccountType=" + AccountType + ", firstName="
				+ firstName + ", lastName=" + lastName + ", aadharNo=" + aadharNo + ", birthDate=" + birthDate
				+ ", MobilePhone=" + MobilePhone + ", AddressLine1=" + AddressLine1 + ", AddressLine2=" + AddressLine2
				+ ", City=" + City + ", ZipCode=" + ZipCode + ", StateProvince=" + StateProvince + ", CountryCode="
				+ CountryCode + ", initialDeposit=" + initialDeposit + "]";
	}
    
	
    
    
}