<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Process Page</title>
</head>
<body>
	<p>
		Thank you for the information. Your hobby of <b>${refData.hobby}</b> and
		aversion of <b>${refData.aversion}</b> will be added to our records,
		eventually.
	</p>
	<form action="Controller">
		<input type="hidden" name="hobby" value="${refData.hobby}"> 
		<input type="hidden" name="aversion" value="${refData.aversion}">
		<p>
			<input type="submit" name="editButton" value="Edit">
		</p>
	</form>
</body>
</html>