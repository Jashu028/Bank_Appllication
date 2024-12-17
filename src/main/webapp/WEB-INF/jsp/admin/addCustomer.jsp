<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h2>Add Customer</h2>
    <form action="addCustomer" method="post">
        <input  type="hidden" name="id" value=1 /><br/>
        <label>Full Name:</label> <input type="text" name="full_name" /><br/>
        <label>Email ID:</label> <input type="email" name="email_id" /><br/>
        <label>Address:</label> <input type="text" name="address"/><br/>
        <label>Initial Balance:</label> <input type="number" name="balance"/><br/>
        <label>Date of Birth:</label> <input type="date" name="dob" /><br/>
        <label>Mobile No:</label> <input type="text" name="mobile_no" /><br/>
        <label>Account Type:</label> <input type="text" name="accountType" /><br/>
        <label>Id Proof:</label> <input type="text" name="idProof"/><br/>
        <button type="submit">Submit</button>
    </form>
</body>
</html>