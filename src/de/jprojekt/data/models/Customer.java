package de.jprojekt.data.models;
import java.util.ArrayList;
import java.util.Date; 


public class Customer extends User {
	
	private ArrayList<BankAccount> myaccounts;
	
	private Employee adviser;
	
	private User user;
	

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
