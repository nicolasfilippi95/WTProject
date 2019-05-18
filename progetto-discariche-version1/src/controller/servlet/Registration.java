package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.service.UserService;


public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UserService userService = new UserService();
		
		if (userService.userExists(request.getParameter("name"), request.getParameter("password"))) {
			// SPARO L'ERRORE
		} else {
			
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
