package com.ibm.bankingapp.responseData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionData {
	private Long transactionId;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	private Long desAccNo;
	
	public TransactionData() {
		
	}

	public TransactionData(Long transactionId, BigDecimal amount, LocalDateTime transactionDate, Long desAccNo) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.desAccNo = desAccNo;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getDesAccNo() {
		return desAccNo;
	}

	public void setDesAccNo(Long desAccNo) {
		this.desAccNo = desAccNo;
	}
	
	
}
