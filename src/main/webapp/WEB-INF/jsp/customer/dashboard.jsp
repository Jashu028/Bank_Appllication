<%@page import="java.util.List"%>
<%@page import="com.genpact.web.bank.project.entity.Transaction"%>
<%@page import="com.genpact.web.bank.project.entity.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Dashboard</title>
<style>
    /* Basic styling for the navigation and table */
    nav {
        float:right;
    }
    
    nav a {
        margin-right: 15px;
        text-decoration: none;
        color: blue;
        font-weight: bold;
    }
    
    table {
        width: 100%;
        border-collapse: collapse;
    }
    
    table, th, td {
        border: 1px solid black;
    }
    
    th, td {
        padding: 10px;
        text-align: left;
    }
    
    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>

    <nav>
        <a href="withdraw">Withdraw</a>
        <a href="deposit">Deposit</a>
        <a href="profile">Profile</a>
        <a href="downloadtransactions">Download Statements</a>
        <a href="logout">Logout</a>
    </nav>

    <%
    Customer customer = (Customer) request.getAttribute("customer");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    
    if (customer == null) {
    %>
        <p>Internal Server Problem</p>
    <%
    } else {
    %>
        <h2>Welcome, <%= customer.getFull_name() %></h2>
        <h3>Account Number: <%= customer.getAccountNumber() %></h3>
        <h3>Balance : <%= customer.getBalance() %></h3>
        
        <h4>Your Transactions</h4>
        <table>
            <thead>
                <tr>
                	<th>Transaction ID</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (transactions != null && !transactions.isEmpty()) {
                    for (Transaction transaction : transactions) {
                %>
                <tr>
                	<td><%= transaction.getId() %></td>
                    <td><%= transaction.getDate() %></td>
                    <td><%= transaction.getType() %></td>
                    <td><%= transaction.getAmount() %></td>
            
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="4">No transactions found.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    <%
    }
    %>

</body>
</html>