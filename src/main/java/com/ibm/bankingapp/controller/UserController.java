package com.ibm.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.LoginForm;
import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.service.JwtService;
import com.ibm.bankingapp.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// Registers only customers
	@PostMapping("/register")
	public void registerUser(@RequestBody RegisterForm form) {
		userService.addUser(form);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form){

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

		if(authentication.isAuthenticated())
			return jwtService.generateToken(form.getUsername());
		else
			return "Login Failed";
	}

}
