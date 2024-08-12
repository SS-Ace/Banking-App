package com.ibm.bankingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Admin;
import com.ibm.bankingapp.model.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	List<Admin> findByEmail(String email);
	List<Admin> findAll();
	List<Admin> findByName(String name);
	void deleteById(Long id);
	
}