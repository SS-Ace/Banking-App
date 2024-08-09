package com.ibm.bankingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Transaction;
import java.time.LocalDateTime;
import com.ibm.bankingapp.model.Account;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByTransactionId(Long transactionId);
	List<Transaction> findAmountByTransactionId(Long transactionId);
	List<Transaction> findTransactionDateByTransactionId(Long transactionId);
	List<Transaction> findByTransactionDate(LocalDateTime transactionDate);
	List<Transaction> findBySourceAccount(Account sourceAccount);
	List<Transaction> findByDestinationAccount(Account destinationAccount);
	List<Transaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	void deleteByTransactionId(Long transactionId);
	
}
