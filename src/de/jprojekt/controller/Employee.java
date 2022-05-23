package de.jprojekt.controller;
import java.util.ArrayList; 

public class Employee extends User {
	
	ArrayList<Customer> mycustomers;
	
	User user;
	
	public boolean addCustomer(Customer c) {
		if (c != null) {
			mycustomers.add(c);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
}
