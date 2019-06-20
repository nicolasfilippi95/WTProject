package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.database.dao.generic.GenericDAO;
import model.beans.Campaign;
import model.beans.Image;

public class ImageDAO extends GenericDAO{
	
	
	public ArrayList<Image> findAllImageByLocality(int localityId){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Image> images = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM image WHERE localityId = ?");
		preparedStatement.setInt(1, localityId);
		resultSet =  preparedStatement.executeQuery();
		while(resultSet.next()) {
			Image image = new Image(resultSet.getInt("id"),resultSet.getString("picture"), resultSet.getString("resolution"), resultSet.getDate("shooting_date"), resultSet.getString("origin"), resultSet.getInt("localityId"));
			images.add(image);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return images;
		
	}
	
/*	public void add(Image image) {
		PreparedStatement preparedStatement = null;
		try {
				preparedStatement =connection.prepareStatement("INSERT into image")
		}
	}
*/	
	
	public ImageDAO(Connection connection) {
		super(connection);
	
	}
	

}
