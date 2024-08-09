package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.formData.AccountFormData;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.repo.AccountRepository;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.TransactionRepository;
import com.ibm.bankingapp.utils.Conversions;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private TransactionRepository transRepo;
	
	@Transactional(rollbackFor = {Exception.class})
	public void addAccount(AccountFormData form) {
		Customer cust  = custRepo.findById(form.getCustomerId()).orElse(null);
		if(cust == null) return;
		List<Account> accounts = accRepo.findByCustomer(cust);
		for(Account account: accounts) {
			if(account.getAccountType().equals(form.getAccountType())) return;
		}
		Account acc = Conversions.convertAccFormToAcc(form, cust);
		accRepo.save(acc);
	}
	
	public Account getAccDetailsByAccNo(Long accNo) {
		return accRepo.findById(accNo).orElse(null);
	}
	
	public List<Transaction> getTransactionHisByAccNo(Long accNo){
		Account acc = accRepo.findById(accNo).orElse(null);
		if(acc != null)
			return transRepo.findBySourceAccount(acc);
		return null;
	}
	
	public Boolean updateAccDetailsByAccNo(Long accNo) {
		
		return false;
	}
	
	public Boolean deleteAccDetailsByAccNo(Long accNo) {
		
		return false;
	}
}
