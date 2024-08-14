package com.ibm.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.formData.AccountForm;
import com.ibm.bankingapp.formData.CustomerForm;
import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.formData.UserForm;
import com.ibm.bankingapp.model.Account;
import com.ibm.bankingapp.model.Admin;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.repo.AccountRepository;
import com.ibm.bankingapp.repo.AdminRepository;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.UserRepository;
import com.ibm.bankingapp.responseData.UserData;
import com.ibm.bankingapp.utils.Conversions;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional(rollbackFor = { ApiException.class,Exception.class })
	public Admin addAdmin(RegisterForm form) throws ApiException {
		form.setPassword(encoder.encode(form.getPassword()));
		if (userRepo.findByUsername(form.getUsername()) != null)
			throw new ApiException("Username already exists");
		User user = userRepo.save(new User(form.getUsername(), form.getPassword(), "ADMIN"));
		return adminRepo.save(new Admin(null, form.getName(), form.getEmail(), user));
	}

	public List<Admin> getAllAdmins() {
		return adminRepo.findAll();
	}

	public Admin getAdminByUserId(Long id) throws ApiException {
		User user = userRepo.findById(id).orElse(null);
		if (user == null)
			throw new ApiException("Admin doesn't exist");
		if(!user.getUserType().equals("ADMIN"))
			throw new ApiException("Entered user id is not associated with admin");
		return adminRepo.findByUser(user);
	}

	@Transactional(rollbackFor = { ApiException.class })
	public void deleteAdminByUserId(Long userId) throws ApiException {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null)
			throw new ApiException("Admin doesn't exist");
		adminRepo.deleteByUser(user);
		userRepo.deleteById(userId);
	}

	@Transactional
	public void addCustomer(RegisterForm form) {
		form.setPassword(encoder.encode(form.getPassword()));
		User user = userRepo.save(new User(form.getUsername(), form.getPassword()));
		customerRepo.save(new Customer(null, form.getName(), form.getEmail(), user));
	}

	public List<UserData> getCustomers() {
		List<Customer> custs = customerRepo.findAll();
		return Conversions.convertCustomerListToCustomerDataList(custs);
	}

	public UserData getCustomerByUserId(Long userId) throws ApiException {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null)
			throw new ApiException("Customer doesn't exist");
		Customer cust = customerRepo.findByUser(user);
		return Conversions.convertCustomerToCustomerData(cust);
	}

	@Transactional
	public UserData updateCustomerUserId(Long userId, CustomerForm form) throws ApiException {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null)
			throw new ApiException("Customer doesn't exist");
		Customer cust = customerRepo.findByUser(user);
		cust.setEmail(form.getEmail() != null ? form.getEmail() : cust.getEmail());
		cust.setName(form.getName() != null ? form.getName() : cust.getName());
		return Conversions.convertCustomerToCustomerData(cust);
	}

	@Transactional
	public UserData updateCustomerCredentials(Long userId, UserForm form) throws ApiException {
		if(form.getPassword() != null)
			form.setPassword(encoder.encode(form.getPassword()));
		User user = userRepo.findById(userId).orElse(null);
		if (user == null)
			throw new ApiException("Customer doesn't exist");
		user.setUsername(form.getUsername() != null ? form.getUsername() : user.getPassword());
		user.setPassword(form.getPassword() != null ? form.getPassword() : user.getPassword());
		Customer cust = customerRepo.findByUser(user);
		return Conversions.convertCustomerToCustomerData(cust);
	}

	@Transactional
	public void deleteCustomer(Long userId) throws ApiException {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null)
			throw new ApiException("Customer doesn't exist");
		Customer cust = customerRepo.findByUser(user);
		if(cust != null) {
			List<Account> accounts = accountRepo.findByCustomer(cust);
			for(Account acc: accounts) {
				accountRepo.delete(acc);
			}
			customerRepo.deleteByUser(user);
			userRepo.deleteById(userId);
		}
	}

	public List<Customer> generateAuditReport() {
		return customerRepo.findAll();
	}

	public Account changeAccType(Long accNo, AccountForm form) throws ApiException {
		if(form.getAccountType() == null) throw new ApiException("Account type is required");
		String accType = form.getAccountType();
		Account acc = accountRepo.findById(accNo).orElse(null);
		if(acc == null) throw new ApiException("Invalid account number");
		acc.setAccountType(accType);
		return accountRepo.save(acc);
	}

	

}