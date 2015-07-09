<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Cookies</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
         prefix="core" %>
</head>
<body>

<h2>Received Cookies</h2>

<p>These are the cookies that were sent to the current controller. 
Any cookies set by the current controller will not be seen until 
the page is reloaded.</p>
<table border="1">
    <tr><th>Name</th><th>Value</th></tr>
    <core:forEach var="element" items="${cookie}">
        <tr>
            <td>${element.value.name}</td>
            <td>${element.value.value}</td>
        </tr>
    </core:forEach>     
</table>
<h2>${marlins}</h2>

<hr>
<form action="/GtwdJsp/ch7/cookie/Controller">
    <input type="submit" name="showCookieButton" value="Show Cookies">
    <input type="submit" name="setCookieButton" value="Set Cookies">
    <input type="submit" name="deleteCookieButton" value="Delete Cookie">
    <input type="submit" name="findCookieButton" value="Find Cookie">
</form><br>
<form action="/GtwdJsp/ch7/cookie/specific/Controller">
    <input type="submit" name="showCookieButton" value="Show Specific Cookies">
    <input type="submit" name="setSpecificCookieButton" value="Set Specific Cookies">
</form>

</body>
</html>