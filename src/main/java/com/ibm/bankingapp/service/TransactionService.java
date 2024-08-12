package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bankingapp.formData.TransactionForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.repo.AccountRepository;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transRepo;

	@Autowired
	private AccountRepository accRepo;

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private JwtService jwtService;

	@Transactional(rollbackOn = {Exception.class})
	public void doTransaction(String token, TransactionForm form) throws Exception {
		Customer cust = getCustomer(token);
		if(!isValidAccNo(token, form.getFromAccNo())) throw new Exception("Incorrect account number");
		Account fromAcc = accRepo.findById(form.getFromAccNo()).orElse(null);
		Account toAcc = accRepo.findById(form.getToAccNo()).orElse(null);
		if(toAcc == null) throw new Exception("Invalid to account number");
		int comparisonResult = fromAcc.getBalance().compareTo(form.getAmount());
		if(comparisonResult == -1) throw new Exception("Insufficient balance");
		fromAcc.setBalance(fromAcc.getBalance().subtract(form.getAmount()));
		toAcc.setBalance(toAcc.getBalance().add(form.getAmount()));
		transRepo.save(new Transaction(fromAcc, toAcc, form.getAmount()));
	}

	// Private Methods

	private Long getUserId(String token) {
		String jwt = token.substring(7);
		return jwtService.extractUserId(jwt);
	}

	private Boolean isValidAccNo(String token, Long accNo) {
		Long id = getUserId(token);
		Customer cust = custRepo.findByUserId(id);
		List<Account> accounts = accRepo.findByCustomer(cust);
		for (Account acc : accounts) {
			if (acc.getAccountNumber().equals(accNo))
				return true;
		}
		return false;
	}

	private Customer getCustomer(String token) throws Exception {
		Long userId = getUserId(token);
		Customer cust = custRepo.findByUserId(userId);
		if (cust == null)
			throw new Exception("Invalid Customer");
		return cust;
	}
	
//	private Account getFromAccount(String token, TransactionForm form) {
//		
//	}
}
