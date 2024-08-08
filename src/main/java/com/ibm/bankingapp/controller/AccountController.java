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

import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService service;
	
	@PostMapping("createaccount")
	public void createAcc(@RequestBody Account model) {
		service.addAccount(model);
	}
	
	@GetMapping("accounts/{id}")
	public Account viewAcc(@PathVariable long id) {
		return service.getAccDetailsByAccNo(id);
	}
	
	@PutMapping("accounts/{id}")
	public Boolean updateAcc(@PathVariable long id) {
		return service.updateAccDetailsByAccNo(id);
	}
	
	@DeleteMapping("customers/{id}")
	public Boolean deleteAcc(@PathVariable long id) {
		return service.deleteAccDetailsByAccNo(id);
	}
	
	@GetMapping("accounts/{id}/transactions")
	public List<Account> viewTransactions(@PathVariable long id) {
		return service.getTransactionHisByAccNo(id);
	}
	

}
