package de.jprojekt.controller;
import java.util.ArrayList; 

import java.sql.Date;

public class Customer extends User {
	
	private String surname;
	private String lastname;
	
	private Date birthday;
	private String address;
	private String telNumber;
	
	
	private ArrayList<BankAccount> myaccounts;
	
	private Employee adviser;
	
	private User user;
	

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}