<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
      <meta charset="utf-8">  
        <title>Confirm Page</title>
  </head>
  <body>
    <p>
      This is a simple HTML page that has a form in it.
     <p>
      The value of the First Name that was sent to 
      this page is: <strong>${helper.data.firstName}</strong>
      <br>
      The value of the Last Name that was sent to 
      this page is: <strong>${helper.data.lastName}</strong>
      <br>
      The value of the Age that was sent to 
      this page is: <strong>${helper.data.age}</strong>
      <br>
      The value of the Email that was sent to 
      this page is: <strong>${helper.data.email}</strong>
      <br>
    <p>
      If there is an error, please select <em>Edit</em>,
      otherwise please select <em>Process</em>.
    <form method="POST" action="Controller">
      <input type="submit" name="editButton" 
                           value="Edit">
      <input type="submit" name="processButton" 
                           value="Process">
    </form>
  </body>
</html>
