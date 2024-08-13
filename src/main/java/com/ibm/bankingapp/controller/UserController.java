package com.ibm.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.LoginForm;
import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.formData.UserForm;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.service.ApiException;
import com.ibm.bankingapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Registers only customers
	@PostMapping("/register")
	public void registerUser(@Valid @RequestBody RegisterForm form) throws ApiException {
		userService.register(form);
	}
	
	@PostMapping("/login")
	public String login(@Valid @RequestBody LoginForm form) throws ApiException{
		return userService.login(form);
	}
	
	@PutMapping("/update")
	public User update(@RequestHeader("Authorization") String token, @RequestBody UserForm form) throws ApiException {
		return userService.updateUser(token, form);
	}

}
