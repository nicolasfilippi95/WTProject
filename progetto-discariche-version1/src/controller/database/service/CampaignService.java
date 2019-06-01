package controller.database.service;

import java.util.ArrayList;
import java.util.List;

import controller.database.dao.CampaignDAO;
import controller.database.service.generic.GenericService;
import model.beans.Campaign;



public class CampaignService extends GenericService{

	
	public ArrayList<Campaign> findAllCampaignbyManagerID(int Id) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		return campaignDAO.findCampaignByManager(Id);
	}

	public void add(Campaign campaign) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		campaignDAO.add(campaign);
		
	}




}
