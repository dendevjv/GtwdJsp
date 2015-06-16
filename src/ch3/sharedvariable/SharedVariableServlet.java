package ch3.sharedvariable;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ch3/sharedVariable/Shared")
public class SharedVariableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int accessCount;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "View.jsp";
		
		incrementSharedVariable();
		
		request.setAttribute("accessCount", accessCount);
		request.getRequestDispatcher(address).forward(request, response);
	}

	synchronized private void incrementSharedVariable() {
		int t = accessCount;
		t++;
		System.out.println("t = " + t);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		accessCount = t;
	}

}
