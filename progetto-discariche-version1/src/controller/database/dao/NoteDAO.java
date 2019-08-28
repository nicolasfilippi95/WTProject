package controller.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.database.dao.generic.GenericDAO;
import model.beans.Image;
import model.beans.Locality;
import model.beans.Note;
import model.beans.User;

public class NoteDAO extends GenericDAO {
	
	
		public Note findNoteById(int id) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Note note = null;
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM note WHERE id = ?");
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					note = new Note(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getBoolean("validity"), resultSet.getString("reliability"), resultSet.getString("comment"), resultSet.getInt("userId"), resultSet.getInt("imageId"));
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
			return note;
		}
		

		
	
	
	
	
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
	
	public ArrayList<Note> findNotesByUserAndImage(int imageId, int userId){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Note> notes = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM note WHERE imageId = ? AND userId=?");
		preparedStatement.setInt(1, imageId);
		preparedStatement.setInt(2, userId);
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
	public int countNoteByImage(int imageId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		int count =0;
		try {
			preparedStatement =connection.prepareStatement("SELECT COUNT(*) AS tot FROM note WHERE imageId=?");
			preparedStatement.setInt(1, imageId);
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
	
	
	public int countNoteByImageFalse(int imageId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		int count =0;
		try {
			preparedStatement =connection.prepareStatement("SELECT COUNT(*) AS tot FROM note WHERE imageId=? AND validity = ?");
			preparedStatement.setInt(1, imageId);
			preparedStatement.setBoolean(2, false);
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
	
	public int countNoteByImageTrue(int imageId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		int count =0;
		try {
			preparedStatement =connection.prepareStatement("SELECT COUNT(*) AS tot FROM note WHERE imageId=? AND validity = ?");
			preparedStatement.setInt(1, imageId);
			preparedStatement.setBoolean(2, true);
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
	
	
	 public void addNote(Note note) {
		 PreparedStatement preparedStatement = null;
		 try {
			 preparedStatement = connection.prepareStatement("INSERT INTO note(date, Validity ,Reliability ,comment, userId,  imageId) VALUES ( ?, ?, ?, ?, ?, ?)");
		 preparedStatement.setDate(1, note.getDate());
		 preparedStatement.setBoolean(2, note.getValidity());
		 preparedStatement.setString(3, note.getReliability());
		 preparedStatement.setString(4, note.getComment());
		 preparedStatement.setInt(5, note.getUserId());
		 preparedStatement.setInt(6, note.getImageId());
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

	public NoteDAO(Connection connection) {
		super(connection);
		
	}

}
