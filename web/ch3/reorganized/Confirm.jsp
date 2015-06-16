<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Confirmation</title>
</head>
<body>
	<p>
		The value of the hobby that was sent to this page is: <b>${helper.data.hobby}</b>
	</p>
	<p>
		The value of the aversion that was sent to this page is: <b>${helper.data.aversion}</b>
	</p>
	<p>
		If there is an error, please select <em>Edit</em>, otherwise please
		select <em>Process</em>.
	</p>

	<form action="Controller">
		<input type="hidden" name="hobby" value="${helper.data.hobby}"> 
		<input type="hidden" name="aversion" value="${helper.data.aversion}">
		<p>
			<input type="submit" name="editButton" value="Edit"> 
			<input type="submit" name="processButton" value="Process">
		</p>
	</form>
</body>
</html>