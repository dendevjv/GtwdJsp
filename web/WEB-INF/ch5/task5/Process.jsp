<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <title>Process Page</title>
  </head>
  <body>
    <p>
      Thank you for your information.<br>
      Your data:<br> 
      <strong>${helper.data.firstName}</strong><br>
      <strong>${helper.data.lastName}</strong><br>
      <strong>${helper.data.age}</strong><br>
      <strong>${helper.data.email}</strong><br>
  	  will be added to our records.
  	</p>
    <form method="POST" action="Controller">
      <p>
      <input type="submit" name="editButton" 
                           value="Edit">
    </form>
    <form method="GET" action="Controller">
      <p>
      <input type="submit" name="editButton" 
                           value="New">
    </form>
    <p>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
             prefix="core" %>
      <core:forEach var="row" items="${database}">
        ${row.id}, 
        ${row.firstName}, 
        ${row.lastName},
        ${row.age},
        ${row.email}<br>
      </core:forEach>
  </body>
</html>

