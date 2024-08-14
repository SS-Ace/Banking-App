package com.ibm.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.responseData.TransactionReportData;
import com.ibm.bankingapp.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/transactions")
	public List<TransactionReportData> getTransactionsByDateRange(){
		System.out.println("Entered");
		return service.getAllTransactions();
	}
	
}
