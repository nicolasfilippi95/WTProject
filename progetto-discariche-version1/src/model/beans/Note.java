package model.beans;

import java.sql.Date;

public class Note {
	private int id;
	private Date date;
	private boolean validity;
	private String reliability;
	private String comment;
	private int userId;
	private int imageId;
	
	public Note(int id, Date date, boolean validity, String reliability, String comment, int userId, int imageId) {
		this.id = id;
		this.date = date;
		this.validity = validity;
		this.reliability = reliability;
		this.comment = comment;
		this.userId = userId;
		this.imageId = imageId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public boolean isValidity() {
		return validity;
	}


	public void setValidity(boolean validity) {
		this.validity = validity;
	}


	public String getReliability() {
		return reliability;
	}


	public void setReliability(String reliability) {
		this.reliability = reliability;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getImageId() {
		return imageId;
	}


	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	
	
	
	
}
