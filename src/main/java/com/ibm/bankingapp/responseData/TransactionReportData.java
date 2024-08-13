package com.ibm.bankingapp.responseData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionReportData extends TransactionData{
	private Long srcAccNo;
	
	public TransactionReportData() {
		
	}

	public TransactionReportData(Long transactionId, BigDecimal amount, LocalDateTime transactionDate, Long srcAccNo, Long desAccNo) {
		super(transactionId, amount, transactionDate, desAccNo);
		this.srcAccNo = srcAccNo;
	}

	public Long getSrcAccNo() {
		return srcAccNo;
	}

	public void setSrcAccNo(Long srcAccNo) {
		this.srcAccNo = srcAccNo;
	}
	
}
