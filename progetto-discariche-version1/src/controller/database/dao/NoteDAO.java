package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.database.dao.generic.GenericDAO;
import model.beans.Image;
import model.beans.Note;

public class NoteDAO extends GenericDAO {
	
	
	public ArrayList<Note> findAllNotesByImage(int imageId){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Note> notes = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM note WHERE imageId = ?");
		preparedStatement.setInt(1, imageId);
		resultSet =  preparedStatement.executeQuery();
		while(resultSet.next()) {
		Note note = new Note(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getBoolean("validity"),resultSet.getString("reliability") , resultSet.getString("comment") , resultSet.getInt("userId"), resultSet.getInt("imageId"));
		notes.add(note);
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
		return notes;
		
	}
	

	public NoteDAO(Connection connection) {
		super(connection);
		
	}

}
