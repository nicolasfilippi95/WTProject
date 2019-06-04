package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.database.dao.generic.GenericDAO;
import model.beans.Locality;

public class LocalityDAO extends GenericDAO {

	public ArrayList<Locality> findAllLocalityByCampaign(int campaignId){
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		ArrayList<Locality> localities = new ArrayList<>();
		try {
			preparedStatement =connection.prepareStatement("SELECT * FROM locality WHERE campaignId=?");
			preparedStatement.setInt(1, campaignId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Locality locality = new Locality(resultSet.getInt("id"), resultSet.getDouble("latitude"), resultSet.getDouble("longitude"), resultSet.getString("name"), resultSet.getString("town"), resultSet.getString("region"), resultSet.getInt("campaignId"));
				localities.add(locality);
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
		
		return localities;
	}
	
	
	
	
	
	public LocalityDAO(Connection connection) {
		super(connection);
	}
	

}
