package com.ibm.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
