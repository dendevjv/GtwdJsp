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
      Thank you for your information. Your hobby of 
      <strong>${helper.data.hobby}</strong> 
      and aversion of
      <strong>${helper.data.aversion}</strong> will be added to 
      account number <b>${helper.data.accountNumber}</b>.
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
        ${row.accountNumber}, 
        ${row.hobby}, 
        ${row.aversion}<br>
      </core:forEach>
  </body>
</html>

