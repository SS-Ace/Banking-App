package com.ibm.bankingapp.formData;

import java.math.BigDecimal;

public class TransactionForm {
	private Long fromAccNo;
	private Long toAccNo;
	private BigDecimal amount;
	
	public TransactionForm() {
		
	}

	public TransactionForm(Long fromAccNo, Long toAccNo, BigDecimal amount) {
		super();
		this.fromAccNo = fromAccNo;
		this.toAccNo = toAccNo;
		this.amount = amount;
	}

	public Long getFromAccNo() {
		return fromAccNo;
	}

	public void setFromAccNo(Long fromAccNo) {
		this.fromAccNo = fromAccNo;
	}

	public Long getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(Long toAccNo) {
		this.toAccNo = toAccNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
