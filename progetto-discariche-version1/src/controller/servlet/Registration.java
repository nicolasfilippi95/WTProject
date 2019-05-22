package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.service.UserService;
import model.beans.User;
import utilities.Utility;


public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		String role = request.getParameter("role");
		
		
		
		if(username == null || email == null || password == null || confirmPassword == null || role  == null
				|| !confirmPassword.contentEquals(password)  || !role.contentEquals("confirmpassword") || 
				(! role.contentEquals("worker")  && !role.contentEquals("manager") )||
				 ( !new Utility().isValidEmailAddress(email)) ){
			response.sendRedirect(request.getContextPath() + "/registration");
			
		}
	    
		
		
		UserService userService = new UserService();
		
		
		
		if (userService.userExists(username, email )) {
			userService.close();
			response.sendRedirect(request.getContextPath() +"/registration.jsp");
		} else {
		boolean role_bool = true; 	 //manager 
		
		if(role ==" worker") {
			role_bool =false;
		}
		
			userService.registerUser(new User(0,username, email, password, role_bool, request.getParameter("experience"),request.getParameter("photo")));
			response.sendRedirect(request.getContextPath() + "/login.jsp");
	
		}	
		
		
		/**
		 * 1) Controllo che non sono gia loggato (nella session controllo se c'è 
		 * userservice = new userservice
		 * if (userservice.userexists(email, username))
		 *     spari lerrore
		 * else {
		 *     4) add(User user)
		 *     5) redirect alla login
		 *     
		 * }
		 */
	}


}
