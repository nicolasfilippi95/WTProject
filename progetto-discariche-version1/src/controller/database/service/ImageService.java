package controller.database.service;

import java.util.ArrayList;

import controller.database.dao.ImageDAO;
import controller.database.dao.LocalityDAO;
import controller.database.service.generic.GenericService;
import model.beans.Image;


public class ImageService extends GenericService{
	
	public ArrayList<Image> findAllImageByLocality(int localityId){
		ImageDAO imageDAO = new ImageDAO(connection);
		return imageDAO.findAllImageByLocality(localityId);
	}

}
