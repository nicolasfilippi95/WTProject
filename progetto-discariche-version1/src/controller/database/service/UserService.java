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


		if(user.getRole() == true) {
			user.setExperience (null);
			user.setPhoto(null);
		}
		UserDAO userDAO = new UserDAO(connection);
		userDAO.add(user);
	}

	public User login(String email, String password) {
		UserDAO userDao = new UserDAO(connection);
		return userDao.findUserByEmailAndPassword(email, password);

	}

}
