package com.genpact.web.bank.project.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.web.bank.project.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer  findByAccountNumber(String accountNumber);

}
