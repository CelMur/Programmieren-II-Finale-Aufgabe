package de.jprojekt.data.models;

import java.util.Date;

public abstract class User {

	int userid;
	String password;

	private String firstname;
	private String lastname;

	private Date bday;
	private String address;
	private int plz;

	//type 0=Customer 1=Employee
	int type;
	
	public abstract void delete();

}
