package com.ibm.bankingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findAll();
	List<Customer> findByEmail(String email);
	List<Customer> findByName(String name);
	Customer findByUser(User user);
	void deleteByUser(User user);
}
