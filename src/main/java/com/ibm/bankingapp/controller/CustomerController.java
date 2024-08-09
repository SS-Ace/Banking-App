package com.ibm.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping("createcustomer")
	public void createCus(@RequestBody Customer model) {
		service.addCustomer(model);
	}
	
	@GetMapping("customers/{id}")
	public Customer viewCus(@PathVariable long id){
		return service.getCustomerById(id);
	}
	
	@PutMapping("customers/{id}")
	public void updateCus(@PathVariable long id,Customer model) {
		service.updateCustomerById(id,model);
	}
	
	@DeleteMapping("customers/{id}")
	public void deleteCus(@PathVariable long id) {
		service.deleteCustomerById(id);
	}

}
