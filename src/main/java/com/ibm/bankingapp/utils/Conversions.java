package com.ibm.bankingapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.ibm.bankingapp.formData.AccountForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.responseData.UserData;
import com.ibm.bankingapp.responseData.TransactionData;
import com.ibm.bankingapp.responseData.TransactionReportData;

public class Conversions {
	
	public static UserData convertCustomerToCustomerData(Customer cust) {
		return new UserData(cust.getName(), cust.getEmail(), cust.getUser().getUsername());
	}
	
	public static List<UserData> convertCustomerListToCustomerDataList(List<Customer> custList) {
		List<UserData> userData = new ArrayList<UserData>();
		for(Customer c: custList) {
			userData.add(new UserData(c.getName(), c.getEmail(), c.getUser().getUsername()));
		}
		return userData;
	}
	
	public static Account convertAccFormToAcc(AccountForm form, Customer cust) {
		 return new Account(form.getAccountType(), form.getBalance(), cust);
	}
	
	public static List<TransactionData> convertTransactionToTransactionData(List<Transaction> transList) {
		List<TransactionData> data = new ArrayList<TransactionData>();
		for(Transaction t: transList) {
			data.add(new TransactionData(t.getTransactionId(), 
					t.getAmount(), t.getTransactionDate(), 
					t.getDestinationAccount().getAccountNumber()));
		}
		return data;
	}
	
	public static List<TransactionReportData> convertTransactionToTransactionReportData(List<Transaction> transList) {
		List<TransactionReportData> data = new ArrayList<TransactionReportData>();
		for(Transaction t: transList) {
			data.add(new TransactionReportData(t.getTransactionId(), 
					t.getAmount(), t.getTransactionDate(),
					t.getSourceAccount().getAccountNumber(),
					t.getDestinationAccount().getAccountNumber()));
		}
		return data;
	}
}
