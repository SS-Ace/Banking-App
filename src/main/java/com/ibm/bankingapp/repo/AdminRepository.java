package com.ibm.bankingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	List<Admin> findByUsernameAndPassword(String username, String password);
	
}
