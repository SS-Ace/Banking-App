package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.repo.TransactionRepository;
import com.ibm.bankingapp.responseData.TransactionReportData;
import com.ibm.bankingapp.utils.Conversions;

@Service
public class ReportService {
	
	@Autowired
	private TransactionRepository transRepo;
	
	public List<TransactionReportData> getAllTransactions(){
		List<Transaction> transactions = transRepo.findAll();
		return Conversions.convertTransactionToTransactionReportData(transactions);
	}
}
