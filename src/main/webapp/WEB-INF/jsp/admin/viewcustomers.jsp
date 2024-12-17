<%@page import="com.genpact.web.bank.project.entity.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
    <style>
    nav{
    	float : right;
    
    }
    
    nav a {
        margin-right: 15px;
        text-decoration: none;
        color: blue;
        font-weight: bold;
    }
    
    </style>
</head>
<body>

	<nav>
	    <a href="/admin/dashboard">Dashboard</a>
	    <a href="addCustomerForm">Add Customer</a>
	    <a href="logout">Logout</a>
	</nav>
	
    <h1>Customer List</h1>

    <%
        // Retrieve the list of customers from the request attribute
        //List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers == null || customers.isEmpty()) {
    %>
        <p>No customers found.</p>
    <%
        } else {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Date of Birth</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>AccountType</th>
                    <th>Id Proof</th>
                    <th>Actions</th>
                    <!-- Add more columns as needed -->
                </tr>
            </thead>
            <tbody>
                <%
                    // Iterate over the list of customers
                    for (Customer customer : customers) {
                %>
                    <tr>
                        <td><%= customer.getId() %></td>
                        <td><%= customer.getFull_name() %></td>
                        <td><%= customer.getEmail_id() %></td>
                        <td><%= customer.getDob() %></td>
                        <td><%= customer.getAddress() %></td>
                        <td><%= customer.getMobile_no() %></td>
                        <td><%= customer.getAccountType() %></td>
                        <td><%= customer.getIdProof() %></td>
                        <td><a href="/admin/update/<%=customer.getId() %>"><button>Update</button></a><a href="/admin/delete/<%=customer.getId() %>"><button>Delete</button></a></td>
                        <!-- Add more columns as needed -->
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
