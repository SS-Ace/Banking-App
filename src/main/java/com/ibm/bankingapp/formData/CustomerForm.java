package com.ibm.bankingapp.formData;

public class CustomerForm {
	private String name;
	private String email;
	
	public CustomerForm() {
		
	}

	public CustomerForm(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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
	
	
}
