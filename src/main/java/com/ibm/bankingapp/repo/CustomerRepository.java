package com.ibm.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.bankingapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	

}
