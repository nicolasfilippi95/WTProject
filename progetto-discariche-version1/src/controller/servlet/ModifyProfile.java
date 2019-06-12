package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.UserService;
import model.beans.User;
import utilities.Utility;

public class ModifyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get data from request
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password =request.getParameter("password");
		String confirmPassword= request.getParameter("confirmPassword");

		//in case of error return to profile' s page
	   if((name== null && email == null && password == null && confirmPassword==null) 
			||(!email.contentEquals("") && !new Utility().isValidEmailAddress(email)) ) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);

			
			return;
		}
		UserService userService = new UserService();
		
		if((!name.contentEquals("")|| !email.contentEquals(""))  &&  userService.userExists(name, email)) {
			userService.close();
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
	     
			return;
		}

		//else get and remove that user from session

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
	    session.removeAttribute("User");

		//update in user the element that have been modified
		if(!name.contentEquals("") ) {
			user.setName(name);
		}
		if(!(email.contentEquals(""))) {
			user.setEmail(email);
		}
		
		
		if(!(password.contentEquals(""))  && password.contentEquals("confirmPassword")) {
			user.setPassword(new Utility().convertToMD5(password));
		}

		//write in database 
		userService.update(user);
		userService.close();

		//put in session
		session.setAttribute("User", user);
		response.sendRedirect(request.getContextPath() + "/home");
		return;
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
