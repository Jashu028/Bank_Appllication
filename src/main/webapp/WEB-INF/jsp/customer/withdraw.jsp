<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw Funds</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        /* Basic CSS styles for the withdraw page */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }

        .container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            margin: auto;
        }

        h2 {
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 15px;
            cursor: pointer;
            text-align: center;
            margin-top: 10px;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Withdraw Funds</h2>
    
    <% String alertMessage = (String) request.getAttribute("alertMessage"); 
       if (alertMessage != null && !alertMessage.isEmpty()) { %>
        <script type="text/javascript">
            alert("<%= alertMessage %>");
        </script>
    <% } %>

    <form action="withdraw" method="post">
        <div class="form-group">
            <label for="amount">Amount to Withdraw:</label>
            <input type="number" id="amount" name="balance" required>
        </div>
        <button type="submit" class="button">Withdraw</button>
    </form>
    ${error}
</div>

</body>
</html>
