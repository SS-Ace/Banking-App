package com.ibm.bankingapp.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.bankingapp.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
