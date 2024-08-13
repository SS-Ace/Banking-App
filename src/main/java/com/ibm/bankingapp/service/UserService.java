package com.ibm.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.bankingapp.formData.UserForm;
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
	public void register(RegisterForm form) throws ApiException {
		form.setPassword(encoder.encode(form.getPassword()));
		if(userRepo.findByUsername(form.getUsername()) != null) 
			throw new ApiException("Username already exists\nEnter different username");
		User user = userRepo.save(new User(form.getUsername(), form.getPassword()));
		custRepo.save(new Customer(null, form.getName(), form.getEmail(), user));
	}
	
	public String login(LoginForm form) throws ApiException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

		if(authentication.isAuthenticated()) {
			User user = userRepo.findByUsername(form.getUsername());
			return jwtService.generateToken(form.getUsername(), user.getId());
		}
		else
			throw new ApiException("Login failed");
	}
	
	public User getUser(String username) {
		return userRepo.findByUsername(username);
	}
	
	public User updateUser(String token, UserForm form) throws ApiException {
		String jwt = token.substring(7);
		String username = jwtService.extractUserName(jwt);
		User user = userRepo.findByUsername(username);
		if(form.getUsername() != null) {
			if(userRepo.findByUsername(form.getUsername()) != null) throw new ApiException("Username already exists");
			else
				user.setUsername(form.getUsername());
		}
		if(form.getPassword() != null) {
			form.setPassword(encoder.encode(form.getPassword()));
			user.setPassword(form.getPassword());
		}
		return userRepo.save(user);
	}
}
