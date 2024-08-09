package com.ibm.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.service.TransactionService;

@RestController
public class TransactionsController {
	@Autowired
	private TransactionService service;
	
//	@PostMapping("transactions")
//	public void transactions(@RequestBody Account model) {
//		service.transactions(model);
//	}

}
