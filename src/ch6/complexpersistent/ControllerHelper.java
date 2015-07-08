package ch6.complexpersistent;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh6;
import shared.HibernateHelper;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh6 {
    private static final String JSP_BASE = "/WEB-INF/ch6/complexpersistent/";
    
    private ComplexDataPersistent data = new ComplexDataPersistent();

    public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
            HttpServletResponse response) {
        super(servlet, request, response);
    }
    
    public Object getData() {
        return data;
    }
    
    public static void initHibernate(HttpServlet servlet) {
        boolean create = Boolean.parseBoolean(servlet.getInitParameter("create"));
        if (create) {
            HibernateHelper.createTable(ComplexDataPersistent.class);
        }
        HibernateHelper.initSessionFactory(ComplexDataPersistent.class);
    }

    @Override
    protected void copyFromSession(Object sessionHelper) {
        if (sessionHelper.getClass() == this.getClass()) {
            ControllerHelper helper = (ControllerHelper) sessionHelper;
            data = helper.data;
            checked = helper.checked;
            selected = helper.selected;
        }
    }

    public void resetNullable() {
        // Checkbox
        data.setExtra(null);
        // Mulitple select
        data.setTeam(null);
        // Radio
        data.setHappiness(0);
    }

    protected String jspLocation(String page) {
        return JSP_BASE + page;
    }

    @ButtonMethod(buttonName = "editButton", isDefault = true)
    public String editMethod() {
        return jspLocation("Edit.jsp");
    }

    @ButtonMethod(buttonName = "confirmButton")
    public String confirmMethod() {
        // Create new data and populate from the query string
        resetNullable();
        fillBeanFromRequest(data);
        setCheckedAndSelected(data);
        // The next JSP address depends on the validity of the data.
        String address;
        if (isValid(data)) {
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
        List<Object> list = HibernateHelper.getListData(getData().getClass());
        request.setAttribute("database", list);
        return jspLocation("Process.jsp");
    }

    @Override
    protected void doGet() throws ServletException, java.io.IOException {
        // Create a new helper object in the session.
        addHelperToSession("helper", SessionData.IGNORE);

        // ControllerEdit.jsp is the default. If the button is null, then this
        // is the page that will display.
        String address = editMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }

    @Override
    protected void doPost() throws ServletException, java.io.IOException {
        // Check if there is already a helper in the session.
        addHelperToSession("helper", SessionData.READ);

        String address = executeButtonMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }
    
}
