package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import controller.database.dao.generic.GenericDAO;
import model.beans.Campaign;
import model.beans.User;


public class CampaignDAO extends GenericDAO{

	public CampaignDAO(Connection connection) {
		super(connection);
	}


	public Campaign findCampaignByID(int id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Campaign campaign = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM campaign WHERE id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				campaign = new Campaign(resultSet.getInt("id"),resultSet.getInt("userId"),resultSet.getString("name") , resultSet.getString("customer"), resultSet.getString("status"));
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
		return campaign;
	}

	// find all campaign by manager
	public ArrayList<Campaign> findCampaignByManager(int id){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Campaign> campaigns = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM campaign WHERE userId = ?");
			preparedStatement.setInt(1, id);
			resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				Campaign campaign = new Campaign(resultSet.getInt("id"), resultSet.getInt("userId"), resultSet.getString("name"), resultSet.getString("customer"), resultSet.getString("status"));
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

	//find all campaign available for worker with id and that have not been already chosen by worker 

	public ArrayList<Campaign> findAllAvailableByWorker(int id){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Campaign> campaigns = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM campaign WHERE status ='STARTED' and id NOT IN (SELECT campaignId from user_campaign WHERE userId = ?)  "); // search all started campaign that haven't yet  been chosen by worker 
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				Campaign campaign = new Campaign(resultSet.getInt("id"),resultSet.getInt("userId"),resultSet.getString("name"), resultSet.getString("customer"), resultSet.getString("status"));
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

	public ArrayList<Campaign> findAllStartedAndChosenByWorker(int id){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Campaign> campaigns = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM campaign WHERE status= 'STARTED' AND id IN (SELECT campaignId FROM user_campaign WHERE userId =?) ");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Campaign campaign = new Campaign(resultSet.getInt("id"), resultSet.getInt("userId"), resultSet.getString("name"), resultSet.getString("customer"), resultSet.getString("status"));
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




	public Campaign add(Campaign campaign ) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO campaign(userId, name, customer, status) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, campaign.getUserId());
			preparedStatement.setString(2, campaign.getName());
			preparedStatement.setString(3, campaign.getCustomer());
			preparedStatement.setString(4, campaign.getStatus());
			preparedStatement.executeUpdate();
			ResultSet  idGen = preparedStatement.getGeneratedKeys();
			if(idGen.next()) {
				campaign.setId(idGen.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return campaign;
	}

	public void update(Campaign campaign) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("UPDATE campaign SET status = ? WHERE id = ?");
			preparedStatement.setString(1, campaign.getStatus());

			preparedStatement.setInt(2, campaign.getId());
			preparedStatement.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement !=null) {
					preparedStatement.close();
				}}catch(SQLException e ) {
					e.printStackTrace();
				}
		}
	}
}
