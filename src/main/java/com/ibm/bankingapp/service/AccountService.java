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
import com.ibm.bankingapp.responseData.AccountData;
import com.ibm.bankingapp.utils.Conversions;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private TransactionRepository transRepo;
	
	@Transactional(rollbackFor = {Exception.class})
	public void addAccount(String token, AccountFormData form) throws Exception{
		Customer cust = getCustomer(token);
		List<Account> accounts = accRepo.findByCustomer(cust);
		for(Account account: accounts) {
			if(account.getAccountType().equals(form.getAccountType())) throw new Exception(form.getAccountType() + " account is already there");
		}
		
		Account acc = Conversions.convertAccFormToAcc(form, cust);
		accRepo.save(acc);
	}
	
	public AccountData getAccDetailsByAccNo(String token, Long accNo) throws Exception {
		Customer cust = getCustomer(token);
		if(!isValidAccNo(token, accNo)) throw new Exception("Incorrect account number");
		Account acc =  accRepo.findById(accNo).orElse(null);
		return new AccountData(acc.getAccountNumber(), acc.getAccountType(), acc.getBalance());
	}
	
	public void updateAccDetailsByAccNo(String token, Long accNo, AccountFormData form) throws Exception {
		Customer cust =  getCustomer(token);
		if(!isValidAccNo(token, accNo)) throw new Exception("Incorrect account number");
		List<Account> accounts = accRepo.findByCustomer(cust);
		for(Account account: accounts) {
			if(account.getAccountNumber().equals(accNo)) {
				account.setAccountType(form.getAccountType() != null ? form.getAccountType() : account.getAccountType());
				account.setBalance(form.getBalance() != null ? form.getBalance(): account.getBalance());
				accRepo.save(account);
			}
		}
	}
	
	public void deleteAccDetailsByAccNo(String token, Long accNo) throws Exception {
		Customer cust =  getCustomer(token);
		if(!isValidAccNo(token, accNo)) throw new Exception("Incorrect account number");
		List<Account> accounts = accRepo.findByCustomer(cust);
		for(Account account: accounts) {
			if(account.getAccountNumber().equals(accNo)) {
				accRepo.deleteById(accNo);
			}
		}
	}
	
	public List<Transaction> getTransactionHisByAccNo(String token, Long accNo) throws Exception{
		Customer cust = getCustomer(token);
		if(!isValidAccNo(token, accNo)) throw new Exception("Incorrect account number");
		Account acc = accRepo.findById(accNo).orElse(null);
		if(acc != null)
			return transRepo.findBySourceAccount(acc);
		return null;
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
		for(Account acc: accounts) {
			if(acc.getAccountNumber().equals(accNo)) return true;
		}
		return false;
	}
	
	private Customer getCustomer(String token) throws Exception {
		Long userId = getUserId(token);
		Customer cust  = custRepo.findByUserId(userId);
		if(cust == null) throw new Exception("Invalid Customer");
		return cust;
	}
}
