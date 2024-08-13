package com.ibm.bankingapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class BankingAppApplicationTests {
    
    @Autowired
    private CustomerRepository custrepo;
    
    @Autowired UserRepository userRepo;
    
    private Customer mod;
    private User user;
    
    @BeforeEach
    void setUp() {
    	mod = new Customer();
    	user = userRepo.save(new User(null, "Test user", "123ht4Fr", "CUSTOMER"));
    }

    @Test
    void testCreateCustomer() {
        mod.setName("Testing1");
        mod.setEmail("testing1@gmail.com");
        mod.setUser(user);
        
        Customer savedCustomer = custrepo.save(mod);
        
        Customer foundCustomer = custrepo.findById(savedCustomer.getId()).orElse(null);
        
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo("Testing1");
        assertThat(foundCustomer.getEmail()).isEqualTo("testing1@gmail.com");
        assertThat(foundCustomer.getUser()).isNotNull();
        assertThat(foundCustomer.getUser().getUsername()).isEqualTo("Test user");
        assertThat(foundCustomer.getUser().getPassword()).isNotNull();
    }
    
    
}
