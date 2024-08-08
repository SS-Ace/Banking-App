package com.ibm.bankingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Transaction;
import java.time.LocalDateTime;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByTransactionId(Long transactionId);
	List<Transaction> findAmountByTransactionId(Long transactionId);
	List<Transaction> findTransactionDateByTransactionId(Long transactionId);
	List<Transaction> findByTransactionDate(LocalDateTime transactionDate);
	
}
