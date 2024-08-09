package com.ibm.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bankingapp.model.Admin;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.repo.AdminRepository;
import com.ibm.bankingapp.repo.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Transactional
    public void rollbackCustomerTransaction(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        }
    }
    
    @Transactional
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public List<Customer> generateAuditReport() {
        return customerRepository.findAll(); 
}
}