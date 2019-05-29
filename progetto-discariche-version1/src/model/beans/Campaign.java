package model.beans;

public class Campaign {
	private int id;
	private int userId;
	private String name;
	private String customer;
	private String status;
	

	public Campaign(int id, int userId, String name, String customer, String status) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.customer = customer;
		this.status = status;
	}


	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
