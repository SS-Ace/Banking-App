package com.ibm.bankingapp.utils;

import com.ibm.bankingapp.formData.AccountFormData;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;

public class Conversions {
	public static Account convertAccFormToAcc(AccountFormData form, Customer cust) {
		 return new Account(null, form.getAccountType(), form.getBalance(), cust);
	}
}
