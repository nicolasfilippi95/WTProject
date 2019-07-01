package controller.database.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.database.dao.generic.GenericDAO;
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
		public void add(String url ,String resolution, Date shooting_date, String origin, int localityId) throws FileNotFoundException {
		PreparedStatement preparedStatement = null;
         FileInputStream fs = null;

		try {
			File f = new  File(url);
			fs = new FileInputStream(f);
			
			preparedStatement =connection.prepareStatement("INSERT into image(picture, resolution, shooting_date,origin, localityId ");
			preparedStatement.setBinaryStream(1, fs, (int)f.length());
			preparedStatement.setString(2, resolution);
			preparedStatement.setDate(3, shooting_date);
			preparedStatement.setInt(4, localityId);
			preparedStatement.executeUpdate();
			System.out.println("imaged stored successfully");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} 
	}
	
	
	public ImageDAO(Connection connection) {
		super(connection);
	
	}
	

}
