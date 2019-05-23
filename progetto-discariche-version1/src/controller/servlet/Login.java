package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.UserService;
import model.beans.User;

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check if you are logged or not
		// if you are already logged go to home
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/home.jsp");

		}

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// check if data are correct
		if (email == null || password == null || email.contentEquals("") || password.contentEquals("")) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}

		// search user with that email and password
		UserService userService = new UserService();
		user = userService.login(email, password);
		userService.close();

		// if return null user is not in database
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");

		}
		// else create session for that user and go to home
		else {
			session = request.getSession(); // will return current session. If current session does not exist, then it
			                                // will create a new session.
			session.setAttribute("User", user);
			response.sendRedirect(request.getContextPath() + "");
		}

	}

}
