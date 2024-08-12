package com.ibm.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.TransactionForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
	@Autowired
	private TransactionService service;
	
	@PostMapping
	public void doTransaction(@RequestHeader("Authorization") String token, @RequestBody TransactionForm form) throws Exception {
		service.doTransaction(token, form);
	}

}
