package controller.database.service;

import java.util.ArrayList;
import java.util.List;

import controller.database.dao.CampaignDAO;
import controller.database.dao.ImageDAO;
import controller.database.dao.LocalityDAO;
import controller.database.dao.NoteDAO;
import controller.database.service.generic.GenericService;
import model.beans.Campaign;
import model.beans.Image;
import model.beans.Locality;
import model.beans.Note;



public class CampaignService extends GenericService{

	
	public ArrayList<Campaign> findAllCampaignbyManagerID(int Id) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		return campaignDAO.findCampaignByManager(Id);
	}

	public void add(Campaign campaign) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		campaignDAO.add(campaign);
		
	}
	
	public ArrayList<Campaign> findAllAvailableByWorker(int Id){
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		ArrayList<Campaign> campaigns = campaignDAO.findAllAvailableByWorker(Id);
		return campaigns;
	}

	public ArrayList<Campaign> findAllStartedAndChosenByWorker(int Id){
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		ArrayList<Campaign> campaigns = campaignDAO.findAllStartedAndChosenByWorker(Id);
		return campaigns;	
	}
	
	public ArrayList<Note> findAllNotesByImage(int imageId){
		NoteDAO noteDAO =new NoteDAO(connection);
		return noteDAO.findAllNotesByImage(imageId);
	}
	
	public ArrayList<Locality> findAllLocalityByCampaign(int campaignId){
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return  localityDAO.findAllLocalityByCampaign(campaignId);
		
	}
	
	
	public ArrayList<Image> findAllImageByLocality(int localityId){
		ImageDAO imageDAO = new ImageDAO(connection);
		return imageDAO.findAllImageByLocality(localityId);
	}


}
