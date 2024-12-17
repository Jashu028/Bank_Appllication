<%@page import="com.genpact.web.bank.project.entity.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Customer</title>
</head>
<body>
    <h2>Update Customer</h2>
<%
    Customer customer = (Customer) request.getAttribute("customer");
%>
<form action="/admin/update/<%=customer.getId() %>" method="post">
    <label>Full Name:</label> 
    <input type="text" name="full_name" value="<%=customer.getFull_name() %>" /><br/>

    <label>Email ID:</label> 
    <input type="email" name="email_id" value="<%=customer.getEmail_id() %>" /><br/>

    <label>Address:</label> 
    <input type="text" name="address" value="<%=customer.getAddress() %>" /><br/>

    <label>Date of Birth:</label> 
    <input type="date" name="dob" value="<%=customer.getDob() %>" /><br/>

    <label>Mobile No:</label> 
    <input type="text" name="mobile_no" value="<%=customer.getMobile_no() %>" /><br/>

    <label>Account Type:</label> 
    <input type="text" name="accountType" value="<%=customer.getAccountType() %>" /><br/>

    <label>Id Proof:</label> 
    <input type="text" name="idProof" value="<%=customer.getIdProof() %>" /><br/>

    <button type="submit">Submit</button>
</form>

</body>
</html>