package com.example.bankingapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

@Entity
@TableGenerator(name="tab", initialValue=100, allocationSize=50)
public class Customer implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
	private int customerId;
    private String lastName;
    private String firstName;
    private String aadharNo;
    @Embedded
    private Address address;
    @OneToOne
    private UserLoginDetails loginDetails;
    private String phone;
    private String email;
    @OneToMany
    private List<Beneficiary> beneficiaries = new ArrayList<>();
    @OneToOne
    private Account account;
    
    public Customer () {        
    }

    public Customer(String lastName,
            String firstName, String aadharNo, Address address, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.aadharNo = aadharNo;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // getters
    public int getCustomerId() {
        return customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
   
    public String getAadharNo() {
		return aadharNo;
	}  

	public Address getAddress() {
		return address;
	}

	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	// setters
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
    
    public void setAddress(Address address) {
		this.address = address;
	}

	public UserLoginDetails getLogin() {
		return loginDetails;
	}

	public void setLogin(UserLoginDetails login) {
		this.loginDetails = login;
	}
    
    public void addBeneficiary(Beneficiary beneficiary) {
    	beneficiaries.add(beneficiary);
    }

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}