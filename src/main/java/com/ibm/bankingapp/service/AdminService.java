package com.ibm.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.bankingapp.formData.RegisterForm;
import com.ibm.bankingapp.model.Admin;
import com.ibm.bankingapp.model.Customer;
import com.ibm.bankingapp.model.User;
import com.ibm.bankingapp.repo.AdminRepository;
import com.ibm.bankingapp.repo.CustomerRepository;
import com.ibm.bankingapp.repo.UserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;

	@Transactional(rollbackFor = { Exception.class })
	public Admin addAdmin(RegisterForm form) throws Exception {
		if(userRepository.findByUsername(form.getUsername()) != null) throw new Exception("Username already exists");
		User user = userRepository.save(new User(form.getUsername(), form.getPassword(), "ADMIN"));
		return adminRepository.save(new Admin(null, user.getId(), form.getName(), form.getEmail()));
	}

	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	public Optional<Admin> getAdminById(Long id) {
		return adminRepository.findById(id);
	}

	@Transactional
	public void deleteCustomerById(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
		}
	}

	@Transactional
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}

	public List<Customer> generateAuditReport() {
		return customerRepository.findAll();
	}
	
//	private void validateAdmin(String token) {
//		String jwt = token.substring(7);
//		String username = jwtService.extractUserName(jwt);
//		User user = userRepository.findByUsername(username);
//		if(!user.getUserType().equals("ADMIN")) throw new
//	}
}