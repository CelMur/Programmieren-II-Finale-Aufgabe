package de.jprojekt.data.models;

import java.util.Date;

public abstract class User {

	public static final int TYPE_CUSTOMER = 0;
	public static final int TYPE_EMPLOYEE = 1;

	private String id;
	private String password;

	private String firstname;
	private String lastname;

	private Date bday;
	private String address;
	private int plz;

	//dont remove pls, ty
	public User() {}
	
	public User(String id, String password, String firstname, String lastname, Date bday, String address, int plz) {
		this.id = id;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.bday = bday;
		this.address = address;
		this.plz = plz;
	}

	//type 0 = Customer 1 = Employee
	private int type;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return DB.getInstance().getPasswordHash(this.id);

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBday() {
		return this.bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPlz() {
		return this.plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
