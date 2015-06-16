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
	<form action="Controller">
		<p>If there is a value for the hobby in the query string, then it
			is used to initialize the hobby element.</p>
		<p>
			Hobby: <input type="text" name="hobby" value="${param.hobby}" />
		</p>
		<p>
			Aversion: <input type="text" name="aversion"
				value="${param.aversion}" />
		</p>
		<input type="submit" name="confirmButton" value="Confirm" />
	</form>
</body>
</html>