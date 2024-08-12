package com.ibm.bankingapp.formData;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class AccountFormData {
	private String accountType;
	private BigDecimal balance;
    
    public AccountFormData() {
    	
    }
    
    public AccountFormData(String accountType, BigDecimal balance) {
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
		return "AccountFormData [accountType=" + accountType + ", balance=" + balance
				+ "]";
	}
	
}
