<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Task 2 View</title>
</head>
<body>
	<h3>Received data</h3>
	<p>Name: ${helper.data.name}</p>
	<p>Country: ${helper.data.country}</p>
	<p>City: ${helper.data.city}</p>
	<form action="Controller">
		<input type="hidden" name="name" value="${helper.data.name}">
		<input type="hidden" name="country" value="${helper.data.country}">
		<input type="hidden" name="city" value="${helper.data.city}">
		Return to <input type="submit" name="editButton" value="Edit">
	</form>
</body>
</html>