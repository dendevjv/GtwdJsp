<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Complex Form - Edit Page</title>
<!-- <link rel="stylesheet" type="text/css" href="/${request.contextPath}/complex.css">  -->
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<form method="post" action="Controller">
	Secret Code
	<input type="password" name="secretCode" value="${helper.data.secretCode}"><br>
	Level of Happiness:<br>
	<input type="radio" name="happiness" value="1" 
        ${helper.checked.happiness["1"]}>Elated
	<input type="radio" name="happiness" value="2"
        ${helper.checked.happiness["2"]}>Ecstatic
	<input type="radio" name="happiness" value="3"
        ${helper.checked.happiness["3"]}>Joyous<br>
	Preferred Extras ${helper.errors.extra}<br>
	<input type="checkbox" name="extra" value="sprinkles"
        ${helper.checked.extra.sprinkles}>Chocolate Sprinkles
	<input type="checkbox" name="extra" value="fudge"
        ${helper.checked.extra.fudge}>Hot Fudge
	<input type="checkbox" name="extra" value="cream"
        ${helper.checked.extra.cream}>Whipped Cream<br>
	Comments<textarea name="comments">${helper.data.comments}</textarea>
    <br>
	Grade
	<select name="grade">
        <option value="4.0" ${helper.selected.grade["4.0"]}>A
        <option value="3.67" ${helper.selected.grade["3.67"]}>A-
        <option value="3.33" ${helper.selected.grade["3.33"]}>B+
        <option value="3.0" ${helper.selected.grade["3.0"]}>B
        <option value="2.67" ${helper.selected.grade["2.67"]}>B-
        <option value="2.33" ${helper.selected.grade["2.33"]}>C+
        <option value="2.00" ${helper.selected.grade["2.0"]}>C
	</select>
    <br>
    Team ${helper.errors.team}
    <select name="team" multiple size="2">
        <option value="heat" ${helper.selected.team.heat}>Heat
        <option value="marlins" ${helper.selected.team.marlins}>Marlins
        <option value="dolphins" ${helper.selected.team.dolphins}>Dolphins
        <option value="panthers" ${helper.selected.team.panthers}>Panthers
    </select>
    <br>
    <input type="submit" name="confirmButton" value="Confirm">
</form>

</body>
</html>