package com.ibm.bankingapp.model;

import java.math.BigDecimal;
import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Account {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long accountNumber; 
	private String accountType; 
    private BigDecimal balance; 
    @ManyToOne 
    @JoinColumn(name = "customer_id") 
    private Customer customer;
    
    @PrePersist
    protected void onCreate() {
        if (accountType == null) {
            accountType = "CUSTOMER";
        }
        if(balance == null) {
        	balance = new BigDecimal("0.0");
        }
    }
    public Account() {
    	
    }
    
    public Account(String accountType, Customer customer) {
		super();
		this.accountType = accountType;
		this.customer = customer;
	}
    
    public Account(String accountType, BigDecimal balance, Customer customer) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customer = customer;
	}
    
	public Account(Long accountNumber, String accountType, BigDecimal balance, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.customer = customer;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ ", customer=" + customer + "]";
	}
    
}
