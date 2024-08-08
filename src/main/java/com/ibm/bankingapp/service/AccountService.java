package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.bankingapp.model.Account;

@Service
public class AccountService {
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
