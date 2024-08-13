package com.ibm.bankingapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.repo.CustomerRepository;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
class BankingAppApplicationTests {
    
    @Autowired
    private CustomerRepository custrepo;
    
    private Customer mod;
    
    @BeforeEach
    void setUp() {
        // Initialize the Customer object before each test
        mod = new Customer();
    }

    @Test
    void testCreateCustomer() {
        mod.setName("Testing1");
        mod.setEmail("1234@gmail.com");
        
        Customer savedCustomer = custrepo.save(mod);
        
        Customer foundCustomer = custrepo.findById(savedCustomer.getId()).orElse(null);
        
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo("Testing1");
        assertThat(foundCustomer.getEmail()).isEqualTo("1234@gmail.com");
    }
}


