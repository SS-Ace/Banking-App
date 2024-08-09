package com.ibm.bankingapp.formData;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class AccountFormData {
	private String accountType;
	private BigDecimal balance;
    private Long customerId;
    
    public AccountFormData(String accountType, BigDecimal balance, Long customerId) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customerId = customerId;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "AccountFormData [accountType=" + accountType + ", balance=" + balance + ", customerId=" + customerId
				+ "]";
	}
	
}
