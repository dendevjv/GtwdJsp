<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
    <p>Please enter you account number to access your data.</p>
    <form method="post" action="Controller">
        <p>
            Account Number ${helper.errors.accountNumber}
            <input type="text" name="accountNumber"
                value="${helper.data.accountNumber}">
            <input type="submit" name="loginButton" value="Login">
        </p>
    </form>
</body>
</html>