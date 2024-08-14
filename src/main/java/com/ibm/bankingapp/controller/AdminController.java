package com.ibm.bankingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.formData.AccountForm;
import com.ibm.bankingapp.formData.CustomerForm;
import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.formData.UserForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Admin;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.service.AdminService;
import com.ibm.bankingapp.service.ApiException;
import com.ibm.bankingapp.repo.*;
import com.ibm.bankingapp.responseData.UserData;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping
    public Admin createAdmin( @RequestBody RegisterForm form) throws ApiException {
        return service.addAdmin(form);
    }
    
    @GetMapping
    public List<Admin> getAllAdmins() {
        return service.getAllAdmins();
    }
    
    @GetMapping("/{id}")
    public Admin getAdminByUserId(@PathVariable Long id) throws ApiException {
        return service.getAdminByUserId(id);
    }

    @DeleteMapping("/{userId}")
    public void deleteAdminByUserId(@PathVariable Long userId) throws ApiException {
        service.deleteAdminByUserId(userId);
    }

    @PostMapping("/customers")
    public void addCustomer(@RequestBody RegisterForm form) {
        service.addCustomer(form);
    }
    
    @GetMapping("/customers/all")
    public List<UserData> getCustomers(){
    	return service.getCustomers();
    }
    
    @GetMapping("/customers/{userId}")
    public UserData getCustomer(@PathVariable Long userId) throws ApiException {
    	return service.getCustomerByUserId(userId);
    }
    
    @PutMapping("/customers/{userId}")
    public UserData updateCustomerByUserId(@PathVariable Long userId, @RequestBody CustomerForm form) throws ApiException {
    	return service.updateCustomerUserId(userId, form);
    }
    
    @PutMapping("/customers/credentials/{userId}")
    public UserData updateCustomerCredentials(@PathVariable Long userId, @RequestBody UserForm form) throws ApiException {
    	return service.updateCustomerCredentials(userId, form);
    }
    
    @PutMapping("/customers/accounts/changeType")
    public Account changeAccType(@PathVariable Long accNo, @RequestBody AccountForm form) throws ApiException {
    	return service.changeAccType(accNo, form);
    }
    
//    @PutMapping("/customers/accounts/deposit")
    
    
    @DeleteMapping("/customers/{userId}")
    public void deleteCustomer(@PathVariable Long userId) throws ApiException {
    	service.deleteCustomer(userId);
    }

}