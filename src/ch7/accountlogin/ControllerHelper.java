package ch7.accountlogin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh5;
import shared.HibernateHelper;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh5 {
	private static final String JSP_BASE = "/WEB-INF/ch7/accountlogin/";
	
    private RequestDataAccount data = new RequestDataAccount();

	public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
	}
	
	static public void initHibernate(HttpServlet servlet) {
		boolean create = Boolean.parseBoolean(servlet.getInitParameter("create"));
		if (create) {
			HibernateHelper.createTable(RequestDataAccount.class);
		}
		
		HibernateHelper.initSessionFactory(RequestDataAccount.class);
	}
	
	public RequestDataAccount getData() {
		return data;
	}

	public void setData(RequestDataAccount data) {
		this.data = data;
	}

	@Override
	protected void copyFromSession(Object sessionHelper) {
		if (sessionHelper.getClass() == this.getClass()) {
			setData(((ControllerHelper) sessionHelper).getData());
		}
	}
	
	protected String jspLocation(String page) {
        return JSP_BASE + page;
    }
	
	@ButtonMethod(isDefault=true)
	public String getMethod() {
	    return jspLocation("Login.jsp");
	}
	
	@ButtonMethod(buttonName = "loginButton")
    public String loginMethod() {
        String address;
        fillBeanFromRequest(data);
        setErrors(data);
        if (isValidProperty("accountNumber")) {
            Object dataPersistent = HibernateHelper.getFirstmatch(
                    data.getClass(), "accountNumber", data.getAccountNumber());
            if (dataPersistent != null) {
                data = (RequestDataAccount) dataPersistent;
            }
            clearErrors();
            address = "Edit.jsp";
        } else {
            address = "Login.jsp";
        }
        return jspLocation(address);
    }
	
	@ButtonMethod(buttonName = "editButton")
    public String editMethod() {
        return jspLocation("Edit.jsp");
    }

	@ButtonMethod(buttonName = "confirmButton")
    public String confirmMethod() {
        fillBeanFromRequest(getData());
        //The next JSP address depends on the validity of the data.
        String address;
        if (isValid(getData())) {
            address = jspLocation("Confirm.jsp");
        } else {
            address = jspLocation("Edit.jsp");
        }
        return address;
    }
	
	@ButtonMethod(buttonName = "processButton")
    public String processMethod() {
        if (!isValid(getData())) {
            return jspLocation("Expired.jsp");
        }
        HibernateHelper.updateDB(getData());
        java.util.List<Object> list =
                HibernateHelper.getListData(getData().getClass());
        request.setAttribute("database", list);
        return jspLocation("Process.jsp");
    }
	
	@Override
    public void doGet()
            throws ServletException, java.io.IOException 
    {
        //Call the method with false to place a new helper in the session
        addHelperToSession("helper", SessionData.IGNORE);

        //Edit.jsp is the only page that will be displayed from a GET request.
        String address = getMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }
	
	@Override
    public void doPost()
            throws ServletException, java.io.IOException 
    {
        addHelperToSession("helper", SessionData.READ);

        String address = executeButtonMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }
}
