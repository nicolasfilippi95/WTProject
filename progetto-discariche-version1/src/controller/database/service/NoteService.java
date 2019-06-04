package controller.database.service;

import java.util.ArrayList;

import controller.database.dao.NoteDAO;
import controller.database.service.generic.GenericService;
import model.beans.Note;

public class NoteService extends GenericService {
	
	public ArrayList<Note> findAllNotesByImage(int imageId){
		NoteDAO noteDAO =new NoteDAO(connection);
		return noteDAO.findAllNotesByImage(imageId);
	}

}
