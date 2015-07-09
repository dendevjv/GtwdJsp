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
        The account number is 
        <b>${helper.data.accountNumber}</b>.
      <p>
        If there are values for the hobby and aversion
        in the query string, then they are used to 
        initialize the hobby and aversion text elements.      
      <p>
        Hobby ${helper.errors.hobby}
        <input type="text" name="hobby" 
                           value="${helper.data.hobby}">
        <br>
        Aversion ${helper.errors.aversion} 
        <input type="text" name="aversion" 
                           value="${helper.data.aversion}">
      <p>
        <input type="submit" name="confirmButton" 
                             value="Confirm">  
      <p>
        <input type="submit" name="newUserButton"
                            value = "New User">   
    </form>
  </body>
</html>
