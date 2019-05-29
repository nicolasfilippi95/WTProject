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


public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		String role = request.getParameter("role");

		if(username == null || email == null || password == null || confirmPassword == null || role  == null
				|| !confirmPassword.contentEquals(password)  || !role.contentEquals("confirmpassword") || 
				(! role.contentEquals("worker")  && !role.contentEquals("manager") )||
				( !new Utility().isValidEmailAddress(email)) ){
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
		}

		UserService userService = new UserService();

		if (userService.userExists(username, email)) {
			userService.close();
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
		} else {

			user = new User(0, username, email, password, false, request.getParameter("experience"), request.getParameter("photo"));			

			user.setRole(role == "worker");

			userService.registerUser(user);

			response.sendRedirect(request.getContextPath() + "/login");

		}	

	}


}
