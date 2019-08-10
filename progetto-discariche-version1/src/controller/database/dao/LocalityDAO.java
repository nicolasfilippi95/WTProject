package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import controller.database.dao.generic.GenericDAO;
import model.beans.Locality;
import model.beans.User;

public class LocalityDAO extends GenericDAO {
	
	
	
	public Locality findLocalityById(int id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Locality locality = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM locality WHERE id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				locality = new Locality(resultSet.getInt("id"), resultSet.getDouble("latitude"), resultSet.getDouble("longitude"), resultSet.getString("name"), resultSet.getString("town"), resultSet.getString("region"), resultSet.getInt("campaignId"));
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return locality;
	}
	
	public int countLocalityByCampaign(int campaignId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		int count =0;
		try {
			preparedStatement =connection.prepareStatement("SELECT COUNT(*) AS tot FROM locality WHERE campaignId=?");
			preparedStatement.setInt(1, campaignId);
			resultSet =preparedStatement.executeQuery();
			while(resultSet.next()) {
			count = resultSet.getInt("tot");
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
		return count;
		
	}

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
	
	
	public Locality findLocalityByNameTownRegion(String name,String town, String region) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Locality locality = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM locality WHERE name=? AND town=? AND  region=?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, town);
			preparedStatement.setString(3, region);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				locality = new Locality(resultSet.getInt("id"), resultSet.getDouble("latitude"), resultSet.getDouble("longitude"), resultSet.getString("name"), resultSet.getString("town"), resultSet.getString("region"), resultSet.getInt("campaignId"));
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return locality;
	}
	


		// REGISTER NEW USER TO DB
		public Locality addLocality(Locality locality){
			PreparedStatement preparedStatement = null;
			try {

				preparedStatement = connection.prepareStatement("INSERT INTO locality(latitude, longitude, name , town , region, campaignId) VALUES ( ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setDouble(1, locality.getLatitude());
				preparedStatement.setDouble(2, locality.getLongitude());
				preparedStatement.setString(3, locality.getName());
				preparedStatement.setString(4, locality.getTown());
				preparedStatement.setString(5, locality.getRegion());
				preparedStatement.setInt(6, locality.getCampaignId());
				preparedStatement.executeUpdate();
				ResultSet  idGen = preparedStatement.getGeneratedKeys();
				if(idGen.next()) {
					locality.setId(idGen.getInt(1));
				}

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
			return locality;

		}
	
	public LocalityDAO(Connection connection) {
		super(connection);
	}
	

}
