<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit</title>
</head>
<body>
	<p>This is a simple HTML form that has a form in it.</p>
	<form action="Controller" method="post">
		<p>If there is a value for the hobby in the query string, then it
			is used to initialize the hobby element.</p>
		<p>
			Hobby ${helper.errors.hobby} 
			<input type="text" name="hobby"	value="${helper.data.hobby}" />
		</p>
		<p>
			Aversion ${helper.errors.aversion} 
			<input type="text" name="aversion" value="${helper.data.aversion}" />
		</p>
		<p>
			Days per week for practicing hobby
			${helper.errors.daysPerWeek}
			<input type="text" name="daysPerWeek" value="${helper.data.daysPerWeek}" />	
		</p>
		<p>
			<input type="submit" name="confirmButton" value="Confirm" />
		</p>
	</form>
</body>
</html>