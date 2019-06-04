package controller.database.service;

import java.util.ArrayList;

import controller.database.dao.LocalityDAO;
import controller.database.service.generic.GenericService;
import model.beans.Locality;

public class LocalityService extends GenericService{
	
	
	public ArrayList<Locality> findAllLocalityByCampaign(int campaignId){
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return  localityDAO.findAllLocalityByCampaign(campaignId);
		
	}

}
