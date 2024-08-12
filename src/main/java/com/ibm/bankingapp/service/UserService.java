package com.ibm.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.bankingapp.formData.LoginForm;
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
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void register(RegisterForm form) {
		form.setPassword(encoder.encode(form.getPassword()));
		User user = userRepo.save(new User(form.getUsername(), form.getPassword()));
		custRepo.save(new Customer(null, user.getId(), form.getName(), form.getEmail()));
	}
	
	public String login(LoginForm form) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

		if(authentication.isAuthenticated()) {
			User user = userRepo.findByUsername(form.getUsername());
			return jwtService.generateToken(form.getUsername(), user.getId());
		}
		else
			return "Login Failed";
	}
	
	public User getUser(String username) {
		return userRepo.findByUsername(username);
	}
}
