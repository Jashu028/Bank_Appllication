<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin_Dashboard</title>
    <style>
     nav {
        float:right;
    }
    
    nav a {
        margin-right: 15px;
        text-decoration: none;
        color: blue;
        font-weight: bold;
    }
    
    h2{
    font-weight:bold;
    color:red;
    font:40px;
    margin-top: 15px;
    margin-right:2px;
    }
    </style>
</head>
<body>

	<nav>
	    <a href="customers">View Customers</a>
	    <a href="addCustomerForm">Add Customer</a>
	    <a href="logout">Logout</a>
	</nav>

    <h2>Welcome, Admin!</h2>
    <p>Logged in as: ${username} </p>


</body>
</html>

