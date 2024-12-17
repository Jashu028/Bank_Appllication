package com.genpact.web.bank.project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genpact.web.bank.project.entity.Customer;
import com.genpact.web.bank.project.entity.Transaction;
import com.genpact.web.bank.project.service.CustomerService;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/customer")
public class CustomerController { 

	@Autowired
    private CustomerService customerService;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "customer/login";
	}

    @PostMapping("/login")
    public String login(@RequestParam String accountno, 
             @RequestParam String password,
             HttpSession session, Model model) {
        if (customerService.login(accountno,password) != null) {
        	session.setAttribute("accountno", accountno);
            return "redirect:/customer/dashboard";
        }else {
        	model.addAttribute("error","Invalid credentials");
            return "customer/login";
		}
        
    }
    
    
 	@GetMapping("/dashboard")
 	public String dashboard(HttpSession session, Model model) {
 	    String accountno = (String) session.getAttribute("accountno");
 	    if (accountno != null) {
 	        Customer customer = customerService.details(accountno);
 	        List<Transaction> transactions = customerService.getAllTransactions(customer);
 	        model.addAttribute("customer", customer);
 	        model.addAttribute("transactions", transactions);
 	        return "customer/dashboard"; 
 	    }

 	    return "redirect:/customer/login";  // Redirect to login if not logged in
 	}
 	
 	@GetMapping("/profile")
 	public String profile(HttpSession session, Model model) {
 		String accountno = (String) session.getAttribute("accountno");
 	    if (accountno != null) {
 	        Customer customer = customerService.details(accountno);
 	        model.addAttribute("customer", customer);
 	        return "customer/profile"; 
 	    }

 	    return "redirect:/customer/login";
 	}
 	
 	@GetMapping("/changepassword")
 	public String changePassword(HttpSession session, Model model) {
 		String accountno = (String) session.getAttribute("accountno");
 		if(accountno!=null) {
 			Customer customer = customerService.details(accountno);
 			String oldpassword = customer.getPassword();
 			model.addAttribute("accountno", accountno);
 			model.addAttribute("oldpassword", oldpassword);
 			return "customer/changepassword";
 		}
 		return "redirect:/customer/login";
 	}
 	

 	@PostMapping("/changepassword")
 	public String changePassword(@RequestParam String accountNo, @RequestParam String oldPassword, 
 	                             @RequestParam String newPassword, Model model) {

 	        Customer customer = customerService.login(accountNo, oldPassword);

 	        if (customer != null) { 
 	                if (oldPassword.equals(newPassword)) {
 	                    model.addAttribute("error", "New password cannot be the same as the old password");
 	                    return "customer/changepassword";  // Return error if passwords are the same
 	                }

 	                customerService.changePassword(customer, newPassword);  // Update the password
 	                return "redirect:/customer/dashboard";
 	            
 	        } else {
 	            model.addAttribute("error", "Old password is incorrect");
 	            return "customer/changepassword";  // Redirect if customer is not found
 	        }
 	    }


    @GetMapping("/deposit")
    public String deposit(HttpSession session, Model model) {
 	    String accountno = (String) session.getAttribute("accountno");
 	    if (accountno != null) {
 	        return "customer/deposit"; 
 	    }
    	return "redirect:/customer/login";
    }
    
    
    @PostMapping("/deposit")
    public String deposit(HttpSession session, @RequestParam Double balance, Model model) {
        String accountno = (String) session.getAttribute("accountno");

        // Check if session and account number exist
        if (accountno != null) {
            Customer customer = customerService.details(accountno);

            // Check if customer exists
            if (customer != null) {

                // Validate that the deposit amount is positive
                if (balance > 0) {
                    customerService.deposit(customer, balance);
                    return "redirect:/customer/dashboard";  // Redirect to dashboard on successful deposit
                } else {
                    model.addAttribute("error", "Deposit amount must be positive");
                    return "customer/deposit";  
                }
            } else {
                return "customer/deposit";  // Redirect to deposit page with error message
            }
        } else {
            return "customer/login";  // Redirect to login if session is invalid
        }
    }


    @GetMapping("/withdraw")
    public String withdraw(HttpSession session, Model model) {
 	    String accountno = (String) session.getAttribute("accountno");
 	    if (accountno != null) {
 	        return "customer/withdraw"; 
 	    }
    	return "redirect:/customer/login";
    }
    
    @PostMapping("/withdraw")
    public String withdraw(HttpSession session, @RequestParam Double balance, Model model) {
        String accountno = (String) session.getAttribute("accountno");

        // Check if session and account number exist
        if (accountno != null) {
            Customer customer = customerService.details(accountno);

            // Check if customer exists
            if (customer != null) {

                // Validate that the deposit amount is positive
                if (balance > 0) {
                    try {
						customerService.withdraw(customer, balance);
	                    return "redirect:/customer/dashboard";
					} catch (Exception e) {
						model.addAttribute("alertmessage",e.getMessage());
						return "customer/withdraw";
					} 
                } else {
                    model.addAttribute("error", "Withdraw amount must be positive");
                    return "customer/withdraw";  
                }
            } else {
                return "customer/withdraw";  // Redirect to deposit page with error message
            }
        } else {
            return "customer/login";  // Redirect to login if session is invalid
        }
    }
    
    

    @GetMapping("/closeaccount")
    public String closeAccount(Model model, HttpSession session) {
    	String accountno = (String) session.getAttribute("accountno");
        if (accountno != null) {
        	Customer customer = customerService.details(accountno);
            try {
                customerService.closeAccount(customer);
                session.invalidate();
                return "redirect:/customer/login";
            } catch (Exception e) {
            	model.addAttribute("alertmessage", e.getMessage());
            	model.addAttribute("customer", customer);
                return "customer/profile";
            }
        }
        return "redirect:/customer/login";
    }
    
    
    @GetMapping("/downloadtransactions")
    public ResponseEntity<?> downloadTransactions(HttpSession session, HttpServletResponse response) throws DocumentException {
        String accountno = (String) session.getAttribute("accountno");
        
        if (accountno != null) {
            Customer customer = customerService.details(accountno);
            
            if (customer != null) {
                // Generate the PDF report
                InputStream pdfStream = customerService.generateTransactionReport(customer);
                
                // Prepare HTTP headers for PDF download
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=transactions.pdf");
                
                // Return the PDF as a downloadable file
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(pdfStream));
            } else {
                // If customer is null, respond with 403 Forbidden
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            // Redirect to login page if session is invalid
            try {
                response.sendRedirect("/customer/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    
    
    @GetMapping("/logout")
 	public String logout(HttpSession session) {
 	    session.invalidate();  // Invalidate the session
 	    return "customer/login";  // Redirect to login page
 	}
    
}
