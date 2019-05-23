package model.beans;

public class Locality {
	private int id;
	private double latitude;
	private double longitude;
	private String name;
	private String town;
	private String region;
	private int campaignId;
	public Locality(int id, double latitude, double longitude, String name, String town, String region,
			int campaignId) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.town = town;
		this.region = region;
		this.campaignId = campaignId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

}
