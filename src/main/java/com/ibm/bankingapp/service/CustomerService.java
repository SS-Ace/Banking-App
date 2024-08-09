package com.ibm.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Transactional(rollbackFor = {Exception.class})
	public void addCustomer(Customer customer) {
		if(custRepo.findByEmail(customer.getEmail()).size() != 0) {
			return;
		}
		custRepo.save(customer);
	}
	
	public Customer getCustomerById(Long id) {
		return custRepo.findById(id).orElse(null);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void updateCustomerById(Long id, Customer customer){
		if(id != customer.getId())
			throw new RuntimeException("Invalid input data");
		Customer cust = custRepo.findById(id).orElse(null);
		if(cust != null)
			custRepo.save(customer);
	}
	
	public void deleteCustomerById(Long id) {
		
	}
}
