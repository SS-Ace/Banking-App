package com.ibm.bankingapp.responseData;

import java.math.BigDecimal;

public class AccountData {
	private Long accountNumber;
	private String accountType;
	private BigDecimal balance;
	
	public AccountData() {
		
	}

	public AccountData(Long accountNumber, String accountType, BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
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
	
	
}
