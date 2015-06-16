package ch3.defaultvalidate;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ch3/defaultValidate/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDataDefault data = new RequestDataDefault();
		data.setHobby(request.getParameter("hobby"));
		data.setAversion(request.getParameter("aversion"));
		request.getSession().setAttribute("refData", data);
		
		String base = "/ch3/dataBean/";
		String url = "";
		if (request.getParameter("confirmButton") != null) {
			url = base + "Confirm.jsp";
		} else if (request.getParameter("processButton") != null) {
			url = base + "Process.jsp";
		} else {
			url = base + "Edit.jsp";
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
