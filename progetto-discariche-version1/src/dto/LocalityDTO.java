package dto;

import model.beans.Locality;

public class LocalityDTO extends Locality{
	String color;


public LocalityDTO(int id, double latitude, double longitude, String name, String town, String region,
			int campaignId, String color) {
		super(id, latitude, longitude, name, town, region, campaignId);
		this.color = color;
	}


public String getColor() {
	return color;
}


public void setColor(String color) {
	this.color = color;
}


}
