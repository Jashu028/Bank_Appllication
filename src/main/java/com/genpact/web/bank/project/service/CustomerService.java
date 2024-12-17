package com.genpact.web.bank.project.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genpact.web.bank.project.Jpa.CustomerRepository;
import com.genpact.web.bank.project.Jpa.TransactionRespository;
import com.genpact.web.bank.project.entity.Customer;
import com.genpact.web.bank.project.entity.Transaction;

@Service
public class CustomerService {

	 	@Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private TransactionRespository transactionRepository;

	    public Customer login(String accountNo, String password) {
	        Customer customer = customerRepository.findByAccountNumber(accountNo);
	        if (customer != null && customer.getPassword().equals(password)) { // In a real app, compare hashed passwords
	            return customer;
	        }
	        return null;
	    }
	    
	    
	    public Customer details(String accountNo) {
	    	
	    	Customer customer = customerRepository.findByAccountNumber(accountNo);
	    	if(customer != null) {
	    		return customer;
	    	}
	    	return null;
	    }

	    public void changePassword(Customer customer, String newPassword) {
	        customer.setPassword(newPassword); // Hash the password before saving
	        customerRepository.save(customer);
	    }

	    public List<Transaction> getLast10Transactions(Customer customer) {
	        return transactionRepository.findTop10ByCustomerOrderByDateDesc(customer);
	    }

	    public void deposit(Customer customer, Double amount) {
	        customer.setBalance(customer.getBalance() + amount);
	        customerRepository.save(customer);

	        Transaction transaction = new Transaction();
	        transaction.setCustomer(customer);
	        transaction.setAmount(amount);
	        transaction.setType("Deposit");
	        transaction.setDate(new java.util.Date());
	        transactionRepository.save(transaction);
	    }

	    public void withdraw(Customer customer, Double amount) throws Exception {
	        if (customer.getBalance() < amount) {
	            throw new Exception("Insufficient balance");
	        }
	        customer.setBalance(customer.getBalance() - amount);
	        customerRepository.save(customer);

	        Transaction transaction = new Transaction();
	        transaction.setCustomer(customer);
	        transaction.setAmount(amount);
	        transaction.setType("Withdraw");
	        transaction.setDate(new java.util.Date());
	        transactionRepository.save(transaction);
	    }
	    
	    @Transactional
	    public void closeAccount(Customer customer) throws Exception {
	        if (customer.getBalance() != 0) {
	            throw new Exception("Withdraw all funds before closing the account");
	        }
	        
	        transactionRepository.deleteByCustomer(customer);
	        
	        customerRepository.delete(customer);
	    }
	    
	    
	    public List<Transaction> getAllTransactions(Customer customer) {
	        return transactionRepository.findByCustomerOrderByDateDesc(customer);
	    }

	    public InputStream generateTransactionReport(Customer customer) throws DocumentException {
	        List<Transaction> transactions = getLast10Transactions(customer);

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, out);
	        document.open();

	        // Create a bold, uppercase font for the title
	        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);

	        // Add title in all caps and bold
	        Paragraph title = new Paragraph("LAST 10 TRANSACTIONS", boldFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        document.add(title);

	        // Add some space after the title
	        document.add(Chunk.NEWLINE);

	        // Create table with 4 columns for Transaction ID, Date, Type, and Amount
	        PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(100);
	        table.setSpacingBefore(10f); // Space before table
	        table.setSpacingAfter(10f);  // Space after table

	        // Add table headers
	        PdfPCell h1 = new PdfPCell(new Paragraph("Transaction ID", boldFont));
	        PdfPCell h2 = new PdfPCell(new Paragraph("Date", boldFont));
	        PdfPCell h3 = new PdfPCell(new Paragraph("Type", boldFont));
	        PdfPCell h4 = new PdfPCell(new Paragraph("Amount", boldFont));

	        // Apply padding to header cells
	        h1.setPadding(10f);
	        h2.setPadding(10f);
	        h3.setPadding(10f);
	        h4.setPadding(10f);

	        // Add headers to table
	        table.addCell(h1);
	        table.addCell(h2);
	        table.addCell(h3);
	        table.addCell(h4);

	        // Add transaction details in each row
	        for (Transaction transaction : transactions) {
	            PdfPCell idCell = new PdfPCell(new Paragraph(transaction.getId().toString()));
	            PdfPCell dateCell = new PdfPCell(new Paragraph(transaction.getDate().toString()));
	            PdfPCell typeCell = new PdfPCell(new Paragraph(transaction.getType()));
	            PdfPCell amountCell = new PdfPCell(new Paragraph(transaction.getAmount().toString()));

	            // Apply padding to data cells
	            idCell.setPadding(8f);
	            dateCell.setPadding(8f);
	            typeCell.setPadding(8f);
	            amountCell.setPadding(8f);

	            // Add cells to table
	            table.addCell(idCell);
	            table.addCell(dateCell);
	            table.addCell(typeCell);
	            table.addCell(amountCell);
	        }

	        // Add the table to the document
	        document.add(table);

	        // Close the document
	        document.close();

	        // Return PDF as InputStream
	        return new ByteArrayInputStream(out.toByteArray());
	    }


	    
	

}
