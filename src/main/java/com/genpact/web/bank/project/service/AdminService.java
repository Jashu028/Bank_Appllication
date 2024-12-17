package com.genpact.web.bank.project.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.web.bank.project.Jpa.AdminRepository;
import com.genpact.web.bank.project.Jpa.CustomerRepository;
import com.genpact.web.bank.project.entity.Admin;
import com.genpact.web.bank.project.entity.Customer;

@Service
public class AdminService {


	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Admin login(String username, String password) {
		 Admin admin= adminRepository.findByUsername(username);
		if(admin != null && admin.getPassword().equals(password)) {
			return admin;
		}
		else {
			return null;
		}
	}
	
	public Admin details(Long id) {
		Optional<Admin> admin = adminRepository.findById(id);
		if(admin.isPresent()) {
			return admin.get();
		}
		return null;
	}
	
	
	public Customer registerCustomer(Customer customer) {
		customer.setAccountNumber(generateAccountNumber());
		customer.setPassword(UUID.randomUUID().toString().substring(0, 8));
		return customerRepository.save(customer);
	}
	
	private String generateAccountNumber() {
		Random random = new Random();
		int accountNumberLength =10;
		StringBuilder accountNumber = new StringBuilder();
		
		for(int i=0; i<accountNumberLength;i++) {
			int digit = random.nextInt(10);
			accountNumber.append(digit);
		}
		return accountNumber.toString();
	}
	
		public void deleteCustomer(Long id) {
	
			customerRepository.deleteById(id);
	
		}

		public Customer updateCustomer (Long id, Customer updatedCustomer) {
			
		Customer customer = customerRepository.findById(id).orElseThrow();

		customer.setFull_name(updatedCustomer.getFull_name());

		customer.setAddress(updatedCustomer.getAddress());

		customer.setMobile_no(updatedCustomer.getMobile_no());

		customer.setEmail_id(updatedCustomer.getEmail_id());

		customer.setDob(updatedCustomer.getDob());

		customer.setIdProof(updatedCustomer.getIdProof());

		return customerRepository.save(customer);

		}
		
	    public Customer getCustomerDetails(Long id) {
	        return customerRepository.findById(id).orElseThrow();
	    }

		public List<Customer> getCustomerDetails () {

			return customerRepository.findAll();

		}
	
}
