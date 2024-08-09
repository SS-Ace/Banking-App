package com.ibm.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
