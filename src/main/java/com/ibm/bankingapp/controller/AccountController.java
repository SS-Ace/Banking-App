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
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.AccountFormData;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.Transaction;
import com.ibm.bankingapp.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService service;
	
	@PostMapping("createaccount")
	public void createAcc(@RequestBody AccountFormData form) {
		service.addAccount(form);
	}
	
	@GetMapping("accounts/{id}")
	public Account viewAcc(@PathVariable long id) {
		return service.getAccDetailsByAccNo(id);
	}
	
	@PutMapping("accounts/{id}")
	public void updateAcc(@PathVariable long id) {
		service.updateAccDetailsByAccNo(id, null);
	}
	
	@DeleteMapping("accounts/{id}")
	public void deleteAcc(@PathVariable long id) {
		service.deleteAccDetailsByAccNo(id);
	}
	
	@GetMapping("accounts/{id}/transactions")
	public List<Transaction> viewTransactions(@PathVariable long id) {
		return service.getTransactionHisByAccNo(id);
	}
	

}
