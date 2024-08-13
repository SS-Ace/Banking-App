package com.ibm.bankingapp.formData;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class UserForm {
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must have only alpha-numeric characters")
	@Size(max = 20, message = "Username cannot contain more than 20 characters")
	String username;
	
	@NotBlank
	@Size(max = 25, message = "Password is invalid")
	String password;
	
	public UserForm() {
		
	}
	
	public UserForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
