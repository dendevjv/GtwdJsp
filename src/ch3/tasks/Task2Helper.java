package ch3.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch3.reorganized.HelperBase;

public class Task2Helper extends HelperBase {
	private DataBean data;

	public Task2Helper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
		data = new DataBean();
	}
	
	public Object getData() {
		return data;
	}
	
	public void doGet() throws ServletException, IOException {
		data.setName(request.getParameter("name"));
		data.setCountry(request.getParameter("country"));
		data.setCity(request.getParameter("city"));
		request.getSession().setAttribute("helper", this);
		
		String address = "";
		if (request.getParameter("editButton") != null) {
			address = "Task2.jsp";
		} else if (request.getParameter("viewButton") != null) {
			address = "Task2view.jsp";
		} else {
			address = "Task2.jsp";
		}
		
		request.getRequestDispatcher(address)
				.forward(request, response);
	}

}
