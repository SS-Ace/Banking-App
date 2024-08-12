package com.ibm.bankingapp.controller;

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

import com.ibm.bankingapp.formData.CustomerUpdateForm;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
//	@PostMapping
//	public void createCustomer(@RequestBody Customer model) {
//		System.out.println("");
//		service.addCustomer(model);
//	}
	
	@GetMapping
	public Customer getCustomer(@RequestHeader("Authorization") String token){
		return service.getCustomerById(token);
	}
	
	@PutMapping
	public void updateCus(@RequestHeader("Authorization") String token, @RequestBody CustomerUpdateForm form) {
		service.updateCustomerById(token, form);
		
	}
	
	@DeleteMapping
	public void deleteCus(@RequestHeader("Authorization") String token) {
		service.deleteCustomerById(token);
	}

}
