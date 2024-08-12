package com.ibm.bankingapp.formData;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class AccountForm {
	private String accountType;
	private BigDecimal balance;
    
    public AccountForm() {
    	
    }
    
    public AccountForm(String accountType, BigDecimal balance) {
		super();
		this.accountType = accountType;
		this.balance = balance;
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

	@Override
	public String toString() {
		return "AccountForm [accountType=" + accountType + ", balance=" + balance
				+ "]";
	}
	
}
