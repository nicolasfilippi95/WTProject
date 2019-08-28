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


	public ArrayList<Locality> findAllLocalityByCampaign(int campaignId){
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return  localityDAO.findAllLocalityByCampaign(campaignId);

	}
	public boolean workerCanJoin(int workerId, int campaignId) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		List<Campaign> campaigns = campaignDAO.findAllAvailableByWorker(workerId);
		

		for (Campaign c : campaigns) {
			if (c.getId() == campaignId) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isJoined(int workerId, int campaignId) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		List<Campaign> campaigns = campaignDAO.findAllStartedAndChosenByWorker(workerId);
		for (Campaign c : campaigns) {
			if (c.getId() == campaignId) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Image> findAllImageByLocality(int localityId){
		ImageDAO imageDAO = new ImageDAO(connection);
		return imageDAO.findAllImageByLocality(localityId);
	}

	public Campaign findcampaignById(int id) {
		CampaignDAO campaignDAO =new CampaignDAO(connection);
		return campaignDAO.findCampaignByID(id);
	}
	
	public void joinToCampaign(int workerId, int campaignId) {
		CampaignDAO campaignDAO = new CampaignDAO(connection);
		campaignDAO.join(workerId, campaignId);
		commit();
	}

	public Locality findLocalityById(int id) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return localityDAO.findLocalityById(id);

	}

	public Locality findLocalityByNameTownRegion(String name,String town,String region) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return localityDAO.findLocalityByNameTownRegion(name, town , region);

	}

	public Locality addLocality(Locality local) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		local = localityDAO.addLocality(local);
		commit();
		return local; 

	}
	public void addImage(Image image) {
		ImageDAO imageDAO = new ImageDAO(connection);
		imageDAO.add(image);
		commit();


	}

	public int countImageByLocality(int locId) {
		ImageDAO imageDAO = new ImageDAO(connection);
		return imageDAO.countImageByLocality(locId);
	}

	public void updateCampaign(Campaign campaign) {
		CampaignDAO campaignDAO =new CampaignDAO(connection);
		campaignDAO.update(campaign);
		commit();
	}

	public int countLocalityByCampaign(int campaignId) {
		LocalityDAO localityDAO = new LocalityDAO(connection);
		return localityDAO.countLocalityByCampaign(campaignId);
	}



	public Note findNoteById(int imageId) {
		NoteDAO noteDAO = new NoteDAO(connection);
		return noteDAO. findNoteById(imageId);
	}

	public ArrayList<Note> findAllNotesByImage(int imageId){
		NoteDAO noteDAO = new NoteDAO(connection);
		return noteDAO.findAllNotesByImage(imageId);

	}

	public int countNoteByImage(int imageId) {
		NoteDAO noteDAO = new NoteDAO(connection);
		return noteDAO.countNoteByImage(imageId);
	}

	public Boolean isThereConflict(int imageId) {
		NoteDAO noteDAO = new NoteDAO(connection);
		int falseNote = noteDAO.countNoteByImageFalse(imageId);
		System.out.println(falseNote +" fdsgg");
		int trueNote = noteDAO.countNoteByImageTrue(imageId);
		System.out.println(trueNote +" fdsgg");
		if(falseNote > 0 && trueNote > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<Note> findNotesByUserAndImage(int imageId, int userId) {
		NoteDAO noteDao = new NoteDAO(connection);
		return noteDao.findNotesByUserAndImage(imageId, userId);
		}
	
	public void addNote(Note note) {
		NoteDAO noteDao = new NoteDAO(connection);
		noteDao.addNote(note);
		commit();
	}
	
	



}
