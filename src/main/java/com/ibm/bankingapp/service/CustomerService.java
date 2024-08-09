package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.repo.AccountRepository;
import com.ibm.bankingapp.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AccountRepository accRepo;
	
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
	
	@Transactional(rollbackFor = {Exception.class})
	public void deleteCustomerById(Long id) {
		Customer cust = custRepo.findById(id).orElse(null);
		if(cust != null) {
			List<Account> accounts = accRepo.findByCustomer(cust);
			for(Account acc: accounts) {
				accRepo.delete(acc);
			}
			custRepo.deleteById(id);
		}
	}
}
