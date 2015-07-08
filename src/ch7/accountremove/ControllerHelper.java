package ch7.accountremove;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HibernateHelper;
import ch7.accountlogin.RequestDataAccount;

public class ControllerHelper extends ch7.accountlogin.ControllerHelper {
	private static final String JSP_BASE = "/WEB-INF/ch7/accountremove/";
	
	public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
	}
	
	protected String jspLocation(String page) {
        return JSP_BASE + page;
    }
	
	@ButtonMethod(buttonName = "removeButton")
    public String removeMethod() {
        HibernateHelper.removeDB(getData());
        setData(new RequestDataAccount());
        return jspLocation("Login.jsp");
    }
	
}
