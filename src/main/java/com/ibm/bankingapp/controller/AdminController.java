package com.ibm.bankingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bankingapp.model.Admin;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.service.AdminService;
import com.ibm.bankingapp.repo.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = service.addAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }
    
    @GetMapping
    public List<Admin> getAllAdmins() {
        return service.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = service.getAdminById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        if (!service.getAdminById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.getAdminById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<Void> rollbackCustomerTransaction(@PathVariable Long customerId) {
        service.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public List<Customer> generateAuditReport() {
        return service.generateAuditReport();
    }
}