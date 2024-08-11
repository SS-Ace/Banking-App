package com.ibm.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void addUser(RegisterForm form) {
		form.setPassword(encoder.encode(form.getPassword()));
		User user = userRepo.save(new User(form.getUsername(), form.getPassword()));
		custRepo.save(new Customer(null, user.getId(), form.getName(), form.getEmail()));
	}
}
