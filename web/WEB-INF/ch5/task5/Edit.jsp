<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
      <meta charset="utf-8">  
        <title>Edit Page</title>
  </head>
  <body>
    <p>
      This is a simple HTML page that has a form in it.
    <form method="POST" action="Controller">
      <p>
        First name ${helper.errors.firstName}
        <input type="text" name="firstName" 
                           value="${helper.data.firstName}">
        <br>
        Last name ${helper.errors.lastName}
        <input type="text" name="lastName" 
                           value="${helper.data.lastName}">
        <br>
        Age ${helper.errors.age} 
        <input type="text" name="age" 
                           value="${helper.data.age}">
        <br>
        Email ${helper.errors.email}
        <input type="text" name="email"
                           value="${helper.data.email}">
      <p>
        <input type="submit" name="confirmButton" 
                             value="Confirm">     
    </form>
  </body>
</html>
