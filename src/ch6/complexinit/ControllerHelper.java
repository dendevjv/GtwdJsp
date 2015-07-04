package ch6.complexinit;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh6;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh6 {
    private static final String JSP_BASE = "/WEB-INF/ch6/complexinit/";
    
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
//        printChecked();
        return jspLocation("Edit.jsp");
    }

    @ButtonMethod(buttonName = "confirmButton")
    public String confirmMethod() {
        // Create new data and populate from the query string
        resetNullable();
        fillBeanFromRequest(data);
        setCheckedAndSelected(data);
//        printChecked();
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
    protected void doGet() throws ServletException, java.io.IOException {
        // Create a new helper object in the session.
        addHelperToSession("helper", SessionData.IGNORE);

        // ControllerEdit.jsp is the default. If the button is null, then this
        // is the page that will display.
        String address = editMethod();
//        String address = executeButtonMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }

    @Override
    protected void doPost() throws ServletException, java.io.IOException {
        // Check if there is already a helper in the session.
        addHelperToSession("helper", SessionData.READ);

        String address = executeButtonMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }
    
    /* Methods for debugging */
    @SuppressWarnings("unused")
    private void printChecked() {
        System.out.println("Contents of \"checked\":");
        System.out.println("\thappiness:");
        printMap(checked.get("happiness"));
        System.out.println("\textra:");
        printMap(checked.get("extra"));
    }
    
    private void printMap(Map<String, String> m) {
        for (String k : m.keySet()) {
            System.out.println(k + " : " + m.get(k));
        }
    }
}
