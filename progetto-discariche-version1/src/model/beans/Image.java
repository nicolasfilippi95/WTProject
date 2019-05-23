package model.beans;

import java.sql.Date;

public class Image {
	private int id;
	private String resolution;
	private Date shooting_date;
	private String origin;
	private int localityId;
	
	public Image(int id, String resolution, Date shooting_date, String origin, int localityId) {
		super();
		this.id = id;
		this.resolution = resolution;
		this.shooting_date = shooting_date;
		this.origin = origin;
		this.localityId = localityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Date getShooting_date() {
		return shooting_date;
	}

	public void setShooting_date(Date shooting_date) {
		this.shooting_date = shooting_date;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getLocalityId() {
		return localityId;
	}

	public void setLocalityId(int localityId) {
		this.localityId = localityId;
	}

}
