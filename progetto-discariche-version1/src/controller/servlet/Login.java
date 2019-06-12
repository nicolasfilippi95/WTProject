package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.UserService;
import model.beans.User;
import utilities.Utility;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// check if you are logged or not
		// if you are already logged go to home		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// check if data are correct
		if (email == null || password == null || email.contentEquals("") || password.contentEquals("")) {			
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		return;
		}

		// search user with that email and password
		UserService userService = new UserService();
		user = userService.login(email,new Utility().convertToMD5(password));
		userService.close();

		// if return null user is not in database
		if (user == null) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			return;
		}
		// else create session for that user and go to home
		else {
			session = request.getSession(); // will return current session. If current session does not exist, then it
			                                // will create a new session.
			session.setAttribute("User", user);
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}
