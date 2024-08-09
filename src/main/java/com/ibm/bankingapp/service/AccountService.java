package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.repo.AccountRepository;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Transactional(rollbackFor = {Exception.class})
	public void addAccount(Account account) {
		
	}
	
	public Account getAccDetailsByAccNo(Long accNo) {
		
		return null;
	}
	
	public List<Account> getTransactionHisByAccNo(Long accNo){
		
		return null;
	}
	
	public Boolean updateAccDetailsByAccNo(Long accNo) {
		
		return false;
	}
	
	public Boolean deleteAccDetailsByAccNo(Long accNo) {
		
		return false;
	}
}
