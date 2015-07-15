<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/catalogue.css" rel="stylesheet" type="text/css">
<title>Browse</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
         prefix="core" %>
</head>
<body>

<div class="layout" id="outer">
    <div class="layout" id="left">
        <core:forEach var="oneItem" items="${allItems}">
            <form method="post" action="Controller">
                <p>
                    ${oneItem.name}
                    <input type="hidden" name="itemId"
                                        value="${oneItem.itemId}">
                    <input type="submit" name="viewItem"
                                        value="View Item">                              
            </form>
        </core:forEach>
    </div>
    <core:if test="${helper.item.itemId != null}">
        <div class="layout" id="right">
            ${helper.item.name}<hr>
            ${helper.item.description}<hr>
            ${helper.item.price}<hr>
            <form action="Controller" method="post">
                <p>
                    <input type="hidden" name="itemId"
                                        value="${helper.item.itemId}">
                    <input type="submit" name="addCart"
                                        value="Add To Cart">
                    <input type="reset">                                         
            </form>
        </div>
    </core:if>
    <div class="layout" id="bottom">
        <form action="Controller" method="post">
            <p>
                <input type="submit" name="viewCart"
                                    value="View Cart">
        </form>
    </div>
</div>

</body>
</html>