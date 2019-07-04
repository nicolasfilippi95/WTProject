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

	public Campaign add(Campaign campaign) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		 campaign = campaignDAO.add(campaign);
		commit();
		return campaign;
		
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
	
	public Campaign findcampaignById(int id) {
		CampaignDAO campaignDAO =new CampaignDAO(connection);
		return campaignDAO.findCampaignByID(id);
	}
	
	public Locality findLocalityById(int id) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return localityDAO.findLocalityById(id);
		
	}
	
	public Locality findLocalityByName(String name) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return localityDAO.findLocalityByName(name);
		
	}
	
	public Locality addLocality(Locality local) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		local = localityDAO.addLocality(local);
		commit();
		return local; 
		
	}
	public void addImage(Image image) {
		ImageDAO ImageDAO = new ImageDAO(connection);
		ImageDAO.add(image);
		commit();
		
		
	}
	
	public Note findNoteById(int imageId) {
		NoteDAO noteDAO = new NoteDAO(connection);
		return noteDAO. findNoteById(imageId);
		}
	



}
