<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Complex Form - Process Page</title>
  </head>
  <body>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
    <p>Thank you for entering your data.</p>
    <ul>
      <li>Secret Code: ${helper.data.secretCode}</li>
      <li>Level of Happiness: ${helper.data.happiness}</li>
      <li>Extras:
        <ul>
        <core:forEach var="element" items="${helper.data.extra}">
          <li>${element}</li>
        </core:forEach>
        </ul>
      </li>
      <li>Comments: ${helper.data.comments}</li>
      <li>Grade: ${helper.data.grade}</li>
      <li>Teams:
        <ul>
        <core:forEach var="element" items="${helper.data.team}">
          <li>${element}</li>
        </core:forEach>
        </ul>
      </li>
    </ul>
    <form method="post" action="Controller">
        <input type="submit" name="editButton" value="Edit">
    </form>
  </body>
</html>



