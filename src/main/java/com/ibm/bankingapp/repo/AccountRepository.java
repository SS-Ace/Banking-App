package com.ibm.bankingapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Customer;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByAccountNumber(Long accountNumber);
	List<Account> findBalanceByAccountNumber(Long accountNumber);
	List<Account> findByCustomer(Customer customer);
	void deleteByAccountNumber(Long accountNumber);
}
