package de.jprojekt.data.models;
import java.util.ArrayList; 

public class Employee extends User {
	
	ArrayList<Customer> customers;
	
	User user;
	
	public void addCustomer(Customer c) {
		customers.add(c);
	}
}
