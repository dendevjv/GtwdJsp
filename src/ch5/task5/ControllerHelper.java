package ch5.task5;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh5;
import shared.HibernateHelper;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh5 {
	private Person data = new Person();

	public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
	}
	
	static public void initHibernate(HttpServlet servlet) {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.setProperty("hibernate.c3p0.min_size", "1");
		props.setProperty("hibernate.c3p0.max_size", "5");
		props.setProperty("hibernate.c3p0.timeout", "300");
		props.setProperty("hibernate.c3p0.max_statements", "50");
		props.setProperty("hibernate.c3p0.idle_test_period", "300");
		
		props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/guidetowebdev");
		props.setProperty("hibernate.connection.username", "guidetowebdev");
		props.setProperty("hibernate.connection.password", "guidetowebdev");
		
		boolean create = Boolean.parseBoolean(servlet.getInitParameter("create"));
		if (create) {
			HibernateHelper.createTable(props, Person.class);
		}
		
		HibernateHelper.initSessionFactory(props, Person.class);
	}
	
	public Person getData() {
		return data;
	}

	public void setData(Person data) {
		this.data = data;
	}

	@Override
	protected void copyFromSession(Object sessionHelper) {
		if (sessionHelper.getClass() == this.getClass()) {
			setData(((ControllerHelper) sessionHelper).getData());
		}
	}
	
	protected String jspLocation(String page) {
        return "/WEB-INF/ch5/task5/" + page;
    }
	
	@ButtonMethod(buttonName = "editButton", isDefault = true)
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
        String address = editMethod();

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
