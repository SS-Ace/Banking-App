package com.ibm.bankingapp.transaction.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.bankingapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
