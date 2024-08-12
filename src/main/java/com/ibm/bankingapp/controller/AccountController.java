package com.ibm.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.AccountFormData;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.responseData.AccountData;
import com.ibm.bankingapp.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService service;
	
	@PostMapping
	public void createAcc(@RequestHeader("Authorization") String token, @RequestBody AccountFormData form) throws Exception {
		service.addAccount(token, form);
	}
	
	@GetMapping("/{accNo}")
	public AccountData viewAcc(@RequestHeader("Authorization") String token, @PathVariable Long accNo) throws Exception {
		return service.getAccDetailsByAccNo(token, accNo);
	}
	
	@PutMapping("/{accNo}")
	public void updateAcc(@RequestHeader("Authorization") String token, @PathVariable Long accNo, @RequestBody AccountFormData form) throws Exception {
		service.updateAccDetailsByAccNo(token, accNo, form);
	}
	
	@DeleteMapping("/{accNo}")
	public void deleteAcc(@RequestHeader("Authorization") String token, @PathVariable Long accNo) throws Exception {
		service.deleteAccDetailsByAccNo(token, accNo);
	}
	
	@GetMapping("/transactions/{accNo}")
	public List<Transaction> viewTransactions(@RequestHeader("Authorization") String token, @PathVariable Long accNo) throws Exception {
		return service.getTransactionHisByAccNo(token, accNo);
	}
	
}
