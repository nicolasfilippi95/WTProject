package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.database.dao.generic.GenericDAO;
import model.beans.Campaign;

public class CampaignDAO {
	
	public CampaignDAO(Connection connection) {
		super(connection);
	}
	
	// find all campaign by manager
	public ArrayList<Campaign> findCampaignByManager(int id){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Campaign> campaigns = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM campaign WHERE userid = ?");
		preparedStatement.setInt(1, id);
		resultSet =  preparedStatement.executeQuery();
		while(resultSet.next()) {
			Campaign campaign = new Campaign(resultSet.getInt("id"), resultSet.getInt("managerId"), resultSet.getString("name"), resultSet.getString("customer"), resultSet.getString("status"));
		campaigns.add(campaign);  
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
		return campaigns;
	}
	
	//find all campaign available for worker 
	
	
	public ArrayList<Campaign> findAllAvailableByWorker(int id){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Campaign> campaigns = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * ");
			
		
		
		
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
	}
	

}
