package de.jprojekt.controller;

public abstract class User {
	String username;
	String pwhash;
	
	//type 0=Customer 1=Employee
	
	int type;
	
	public abstract void delete();

}
