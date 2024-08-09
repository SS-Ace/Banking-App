package com.ibm.bankingapp.formData;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class AccountFormData {
	private String accountType; 
    private BigDecimal balance;
    private Long customerId;
}
