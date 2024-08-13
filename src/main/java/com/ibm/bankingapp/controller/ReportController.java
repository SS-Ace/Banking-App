package com.ibm.bankingapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.responseData.TransactionReportData;

@RestController
@RequestMapping("/reports")
public class ReportController {
	
	public List<TransactionReportData> getTransactionsByDateRange(){
		return null;
	}
}
