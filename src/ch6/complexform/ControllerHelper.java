package ch6.complexform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh5;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh5 {
    private RequestDataComplex data = new RequestDataComplex();

    public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
            HttpServletResponse response) {
        super(servlet, request, response);
    }

    public Object getData() {
        return data;
    }

    @Override
    protected void copyFromSession(Object sessionHelper) {
        if (sessionHelper.getClass() == this.getClass()) {
            data = ((ControllerHelper) sessionHelper).data;
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
        return "/WEB-INF/ch6/complexform/" + page;
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
        return jspLocation("Process.jsp");
    }

    @Override
    public void doGet() throws ServletException, java.io.IOException {
        // Call the method with false to place a new helper in the session
        addHelperToSession("helper", SessionData.READ);

        // Edit.jsp is the only page that will be displayed from a GET request.
        String address = executeButtonMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }
}
