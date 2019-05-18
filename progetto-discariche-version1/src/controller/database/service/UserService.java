package controller.database.service;

import controller.database.dao.UserDAO;
import controller.database.service.generic.GenericService;
import model.beans.User;
import utilities.Utility;

public class UserService extends GenericService {

	public boolean userExists(String username, String email) {
		UserDAO userDAO = new UserDAO(connection);
		User user = userDAO.findUserByNameOrEmail(username, email);
		return (user != null);
	}

	public void registerUser(User user) {
		if (new Utility().isValidEmailAddress(user.getEmail())) {
		
			// VARI CONTROLLI SE CE NE SONO
			// SE L'USER E' MANAGER METTO A NULL TUTTI I CAMPI CHE HANNO PHOTO E COSI VIA
			UserDAO userDAO = new UserDAO(connection);
			userDAO.add(user);			
		
		} else {
			// ALTRO ERRORE
		}
		
	}
}