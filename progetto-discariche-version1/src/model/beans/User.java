package model.beans;

public class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private boolean role;
	private String experience;
	private String photo;
	
	public User(int id, String name, String email, String password, boolean role, String experience, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.experience = experience;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String n ) {
		this.name = n;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole (boolean role) {
		this.role = role;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getPhoto() {
		return photo ;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
