<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String url = "";
	if (request.getParameter("confirmButton") != null) {
		url = "Confirm.jsp";
	} else if (request.getParameter("processButton") != null) {
		url = "Process.jsp";
	} else {
		url = "Edit.jsp";
	}
	
	RequestDispatcher disp = request.getRequestDispatcher(url);
	disp.forward(request, response);
%>
