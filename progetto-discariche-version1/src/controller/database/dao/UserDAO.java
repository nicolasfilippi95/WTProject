package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.database.dao.generic.GenericDAO;
import model.beans.User;

public class UserDAO extends GenericDAO {

	// REGISTER NEW USER TO DB
	public void add(User user){
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement("INSERT INTO user(name, email , password , role, experience , photo) VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setBoolean(4, user.getRole());
			preparedStatement.setString(5, user.getExperience());
			preparedStatement.setString(6, user.getPhoto());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (preparedStatement != null) 
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// FIND NAME OR EMAIL TO REGISTER A NEW USER
	public User findUserByNameOrEmail(String name, String email) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email  = ? or name = ? ");
			preparedStatement.setString(0, email);
			preparedStatement.setString(1, name);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"), 
						resultSet.getString("password"),resultSet.getBoolean("role"),resultSet.getString("experience"), 
						resultSet.getString("photo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null) 
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	// LOGIN
	public User findUserByEmailAndPassword(String email, String password) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? and password = ? ");
			preparedStatement.setString(0, email);
			preparedStatement.setString(1, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), 
						resultSet.getString("password"), resultSet.getBoolean("role"), resultSet.getString("experience"), 
						resultSet.getString("photo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null) 
					preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;	
	}
	

// public void update(User user) {}
	
	
	public UserDAO(Connection connection) {
		super(connection);
	}
}