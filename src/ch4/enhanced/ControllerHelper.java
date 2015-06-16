package ch4.enhanced;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch3.defaultvalidate.RequestDataDefault;
import shared.ButtonMethod;
import shared.HelperBaseCh4;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh4 {
	private static final String JSP_BASE = "/WEB-INF/ch4/enhanced/";
	
	private RequestDataDefault data;

	public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
		data = new RequestDataDefault();
	}
	
	public Object getData() {
		return data;
	}

	@Override
	public void copyFromSession(Object sessionHelper) {
		if (sessionHelper.getClass() == this.getClass()) {
			data = ((ControllerHelper) sessionHelper).data;
		}
	}

	protected String jspLocation(String page) {
		return JSP_BASE + page;
	}
	
	@ButtonMethod(buttonName="editButton", isDefault=true)
	public String editMethod() {
		return jspLocation("Edit.jsp");
	}
	
	@ButtonMethod(buttonName="confirmButton")
	public String confirmMethod() {
		fillBeanFromRequest(data);
		return jspLocation("Confirm.jsp");
	}
	
	@ButtonMethod(buttonName="processButton")
	public String processMethod() {
		return jspLocation("Process.jsp");
	}

	@Override
	public void doGet() throws ServletException, IOException {
		addHelperToSession("helper", SessionData.READ);
		
		String address = executeButtonMethod();
		
		request.getRequestDispatcher(address).forward(request, response);
	}
	
}
