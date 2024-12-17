package com.genpact.web.bank.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genpact.web.bank.project.entity.Admin;
import com.genpact.web.bank.project.entity.Customer;
import com.genpact.web.bank.project.service.AdminService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/admin")
public class AdminController {

	 	@Autowired
	    private AdminService adminService;
	 	
	 	@GetMapping("/login")
	 	public String loginForm(Model model) {
	 	    return "admin/login"; // JSP for login
	 	}

	 	@PostMapping("/login")
	 	public String login(@RequestParam String username, 
	 	                    @RequestParam String password,
	 	                    HttpSession session, Model model) {
	 		
	 		Admin admin = adminService.login(username, password);
	 	    
	 	    if ( admin !=null) {
	 	        session.setAttribute("adminId", admin.getId());  // Set session attribute
	 	        return "redirect:/admin/dashboard";  // Redirect to dashboard after successful login
	 	    } else {
	 	        model.addAttribute("error", "Invalid email or password");
	 	        return "admin/login";  // Back to login page with error message
	 	    }
	 	}
	 	

	 	@GetMapping("/dashboard")
	 	public String dashboard(HttpSession session, Model model) {
	 	    Long adminId = (Long) session.getAttribute("adminId");
	 	    if (adminId != null) {
	 	    	Admin admin = adminService.details(adminId);
	 	        model.addAttribute("username",admin.getUsername() );
	 	        return "admin/dashboard"; // JSP for admin dashboard
	 	    }

	 	    return "redirect:/admin/login";  // Redirect to login if not logged in
	 	}
	 	

	    @GetMapping("/addCustomerForm")
	    public String addCustomerForm(HttpSession session ,Model model) {
	    	Long adminId = (Long) session.getAttribute("adminId");
	 	    if (adminId != null) {
	 	    	model.addAttribute("customer", new Customer());
		        return "admin/addCustomer"; // Return the JSP page name
	 	    }
	 	    return "redirect:/admin/login"; 
	    }

	    @PostMapping("/addCustomer")
	    public String addCustomer(@ModelAttribute Customer customer, Model model) {
	        Customer status = adminService.registerCustomer(customer);
	        model.addAttribute("status", status);
	        return "redirect:/admin/customers";
	    }

	    @GetMapping("/customers")
	    public String listCustomers(HttpSession session,Model model) {
	    	Long adminId = (Long) session.getAttribute("adminId");
	 	    if (adminId != null) {
		        List<Customer> customers = adminService.getCustomerDetails();
		        model.addAttribute("customers", customers);
		        return "admin/viewcustomers"; // Return the JSP page name 
	 	    }
	 	    return "redirect:/admin/login"; 
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String deleteCustomer(HttpSession session,@PathVariable Long id) {
	    	Long adminId = (Long) session.getAttribute("adminId");
	 	    if (adminId != null) {
	 	    	adminService.deleteCustomer(id);
		        return "redirect:/admin/customers";
	 	    }
	 	    return "redirect:/admin/login"; 
	    }
	    
	    	    
	    @GetMapping("/update/{id}")
	    public String getUpdateCustomer(HttpSession session,@PathVariable Long id, Model model) {
	    	Long adminId = (Long) session.getAttribute("adminId");
	 	    if (adminId != null) {
	 	    	Customer customer = adminService.getCustomerDetails(id);
		    	model.addAttribute("customer", customer);
		    	return "admin/updatecustomer";
	 	    }
	 	    return "redirect:/admin/login"; 
	    }
	    
	    @PostMapping("/update/{id}")
	    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
	        adminService.updateCustomer(id, customer);
	        return "redirect:/admin/customers";
	    }
	    
	    
	 	// Logout
	 	@GetMapping("/logout")
	 	public String logout(HttpSession session) {
	 	    session.invalidate();  // Invalidate the session
	 	    return "admin/login";  // Redirect to login page
	 	}
	}

	





