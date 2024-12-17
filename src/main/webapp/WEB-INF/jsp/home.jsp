<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
	nav {
		margin-top : 15px;
        margin-left : 80%;
    }
    
    nav a {
        margin-right: 15px;
        text-decoration: none;
        color: blue;
        font-weight: bold;
    }
    div{
    	display : flex;
    	justify-content : center;
    }
    
    h1{
    	font-size:50px;
    }
		

</style>
</head>
<body>
<nav>
    <a href="/admin/login">Admin Login</a>
    <a href="/customer/login">Customer Login</a>
</nav>
<div><h1>Bank Application</h1></div>
</body>
</html>