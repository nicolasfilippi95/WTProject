package controller.database.service.generic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class GenericService {
	
	protected DataSource dataSource;
	protected Connection connection;
	
	public GenericService() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/DataSource");
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} 
	}
	
	public void commit() {
		try {
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
