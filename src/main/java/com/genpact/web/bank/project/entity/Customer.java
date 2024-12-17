package com.genpact.web.bank.project.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String full_name;
		private String address;
		private String email_id;
		private String accountType;
		
		@Column(nullable = false)
		private Double balance;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date dob ;
		private String idProof;
		private String accountNumber;
		private String password;
		private String mobile_no;
//		private String adhaar_no;
}
