package ch7.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh4;

public class ControllerHelper extends HelperBaseCh4 {
	private static final String JSP_BASE = "/WEB-INF/ch7/cookie/";
	
	public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
	}

	@Override
	public void copyFromSession(Object sessionHelper) {
	}

	protected String jspLocation(String page) {
		return JSP_BASE + page;
	}
	
	@ButtonMethod(buttonName="showCookieButton", isDefault=true)
	public String showMethod() {
	    return jspLocation("ShowCookies.jsp");
	}
	
	@ButtonMethod(buttonName="setCookieButton")
	public String setMethod() {
	    Cookie dolphins = new Cookie("dolphins", "The Dolphins are here to stay");
	    dolphins.setPath("/");
	    response.addCookie(dolphins);
	    
	    Cookie marlins = new Cookie("marlins", "The Marlins will be gone soon");
	    marlins.setMaxAge(15);
	    marlins.setPath("/");
	    response.addCookie(marlins);
	    
	    return jspLocation("ShowCookies.jsp");
	}
	
	@ButtonMethod(buttonName="setSpecificCookieButton")
	public String setSpecificMethod() {
	    Cookie specific = new Cookie("specific", "Not all pages can see this cookie");
	    specific.setMaxAge(15);
	    response.addCookie(specific);
	    
	    return jspLocation("ShowCookies.jsp");
	}
	
	@ButtonMethod(buttonName="deleteCookieButton")
	public String deleteMethod() {
	    Cookie marlins = new Cookie("marlins", "bye-bye");
	    marlins.setMaxAge(0);
	    marlins.setPath("/");
	    response.addCookie(marlins);
	    
	    return jspLocation("ShowCookies.jsp");
	}
	
	@ButtonMethod(buttonName="findCookieButton")
	public String findMethod() {
	    Cookie[] cookieArray = request.getCookies();
	    Cookie marlins = null;
	    if (cookieArray != null) {
	        for (Cookie cookie : cookieArray) {
	            if (cookie.getName().equals("marlins")) {
	                marlins = cookie;
	            }
	        }
	    }
	    String result = "The Marlins have left town";
	    if (marlins != null) {
	        result = marlins.getValue();
	    }
	    request.setAttribute("marlins", result);
	    
	    return jspLocation("ShowCookies.jsp");
	}
	
	@Override
	public void doGet() throws ServletException, IOException {
		String address = executeButtonMethod();
		
		request.getRequestDispatcher(address).forward(request, response);
	}
	
}
