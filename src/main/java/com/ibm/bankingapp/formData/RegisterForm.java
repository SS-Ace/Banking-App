package com.ibm.bankingapp.formData;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class RegisterForm {
	
	@NotBlank(message = "Name is required")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must have only alphabetical characters")
	String name;
	
	@Email(message = "Enter a valid email address")
	@NotBlank(message = "Email address is required")
	String email;
	
	@NotBlank(message = "Username is required")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must have only alpha-numeric characters")
	String username;
	
	@NotBlank
	@Size(
		min = 8, max = 25, message = "Passwords must have atleast 8 characters and maximum 25 characters"
	)
	String password;	
	
	public RegisterForm() {
		
	}

	public RegisterForm(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
