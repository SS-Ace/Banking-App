package com.ibm.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.UserForm;
import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.service.JwtService;
import com.ibm.bankingapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Registers only customers
	@PostMapping("/register")
	public void registerUser(@RequestBody RegisterForm form) {
		userService.register(form);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserForm form){
		return userService.login(form);
	}
	
	@PutMapping("/update")
	public User update(@RequestHeader("Authorization") String token, @RequestBody UserForm form) throws Exception {
		return userService.updateUser(token, form);
	}

}
