<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/catalogue.css" rel="stylesheet" type="text/css">
<title>Process Page</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
         prefix="core" %>
</head>
<body>

<div class="layout" id="outer">
    <div class="layout" id="left">
        <table>
            <core:forEach var="oneItem" items="${helper.cart.items}">
                <tr>
                    <td>${oneItem.itemId}</td>
                    <td>${oneItem.name}</td>
                    <td>${oneItem.price}</td>
                </tr>
            </core:forEach>
        </table>
    </div>
    
    <div class="layout" id="right">
        <p>Thank you for your purchase. However, since your
        credit cart info and personal data were never
        collected, it will be difficult to bill you and send
        you your merchandise.</p>
        <p>Please return again when someone has completed the
        rest of this application.</p>
    </div>
    
    <div class="layout" id="bottom">
        <p>
            You ordered ${helper.cart.count} items for a total bill
            of ${helper.cart.totalAsCurrency}.
        </p>
            
        <form method="post" action="Controller">
            <p>
                <input type="submit" name="shop"
                                    value="Continue Shopping">
                <input type="submit" name="emptyCart"
                                    value="Empty Cart">
        </form>
    </div>
</div>

</body>
</html>