package com.genpact.web.bank.project.Jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.web.bank.project.entity.Customer;
import com.genpact.web.bank.project.entity.Transaction;

@Repository
public interface TransactionRespository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop10ByCustomerOrderByDateDesc(Customer customer);
    
    List<Transaction> findByCustomerOrderByDateDesc(Customer customer);
    
    void deleteByCustomer(Customer customer);

}
