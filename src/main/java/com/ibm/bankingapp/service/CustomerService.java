package com.ibm.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.formData.CustomerForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.repo.AccountRepository;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.UserRepository;
import com.ibm.bankingapp.responseData.UserData;
import com.ibm.bankingapp.utils.Conversions;

@Service
public class CustomerService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Transactional(rollbackFor = {Exception.class})
	public void addCustomer(Customer customer) {
		if(custRepo.findByEmail(customer.getEmail()).size() != 0) {
			return;
		}
		custRepo.save(customer);
	}
	
	public UserData getCustomer(String token) {
		Long id = getUserId(token);
		User user = userRepo.findById(id).orElse(null);
		Customer cust = custRepo.findByUser(user);
		return Conversions.convertCustomerToCustomerData(cust);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public UserData updateCustomer(String token, CustomerForm form){
		Long id = getUserId(token);
		Customer cust = custRepo.findById(id).orElse(null);
		cust.setEmail(form.getEmail());
		cust.setName(form.getName());
		return Conversions.convertCustomerToCustomerData(cust);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public void deleteCustomerById(String token) {
		Long id = getUserId(token);
		User user = userRepo.findById(id).orElse(null);
		Customer cust = custRepo.findByUser(user);
		if(cust != null) {
			List<Account> accounts = accRepo.findByCustomer(cust);
			for(Account acc: accounts) {
				accRepo.delete(acc);
			}
			custRepo.deleteByUser(user);
			userRepo.deleteById(id);
		}
	}
	
	private Long getUserId(String token) {
		String jwt = token.substring(7);
		return jwtService.extractUserId(jwt);
	}
}
