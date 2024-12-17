<%@page import="com.genpact.web.bank.project.entity.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
    // Assuming the customer details are set in the request
    Customer customer = (Customer) request.getAttribute("customer");

    // Format the date to only display yyyy-MM-dd
    String formattedDate = "";
    if (customer != null && customer.getDob() != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = sdf.format(customer.getDob());
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        /* Basic CSS styles for the profile page */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h2 {
            color: #333;
        }

        .profile-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 650px;
            margin: auto;
        }

        .nav-links {
            margin: 10px 0;
        }

        .nav-links a {
            margin-right: 15px;
            text-decoration: none;
            color: #007bff;
        }

        .customer-details {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }

        .left, .right {
            width: 48%;
        }

        .customer-details div {
            margin-bottom: 10px;
        }

        .button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 15px;
            cursor: pointer;
            text-align: center;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<%-- Check if an alert message is set in the request --%>
<%
    String alertMessage = (String) request.getAttribute("alertmessage");
    if (alertMessage != null && !alertMessage.isEmpty()) {
%>
        <script type="text/javascript">
            // Display alert with the message passed from the backend
            alert("<%= alertMessage %>");
        </script>
<%
    }
%>

<div class="profile-container">
    <nav class="nav-links">
        <a href="dashboard">Dashboard</a>
        <a href="withdraw">Withdraw</a>
        <a href="deposit">Deposit</a>
        <a href="changepassword">Change Password</a>
        <a href="logout">Logout</a>
        <button class="button" onclick="confirmCloseAccount()">Close Account</button>
    </nav>

    <h2>Profile Details</h2>

    <% if (customer != null) { %>
        <div class="customer-details">
            <div class="left">
                <div><strong>Full Name:</strong> <%= customer.getFull_name() %></div>
                <div><strong>Email:</strong> <%= customer.getEmail_id() %></div> 
                <div><strong>Date of Birth:</strong> <%= formattedDate %></div>
                <div><strong>Address:</strong> <%= customer.getAddress() %></div> 
            </div>
            <div class="right">
                <div><strong>Account Number:</strong> <%= customer.getAccountNumber() %></div> 
                <div><strong>Account Type:</strong> <%= customer.getAccountType() %></div> 
                <div><strong>ID Proof:</strong> <%= customer.getIdProof() %></div>  
                <div><strong>Mobile No:</strong> <%= customer.getMobile_no() %></div>
            </div>
        </div>
    <% } else { %>
        <p>No customer details available.</p>
    <% } %>
</div>

<script type="text/javascript">
    // JavaScript function to confirm account closure
    function confirmCloseAccount() {
        var confirmation = confirm("Are you sure you want to close your account?");
        if (confirmation) {
            // If the user confirms, redirect to the close account URL
            window.location.href = "closeaccount";
        }
    }
</script>

</body>
</html>
