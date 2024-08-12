package com.ibm.bankingapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.ibm.bankingapp.formData.AccountForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.responseData.TransactionData;

public class Conversions {
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
}
