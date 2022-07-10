package de.jprojekt.data.models;

import java.util.ArrayList;
import java.util.Date;

public class Employee extends User {
	private ArrayList<Customer> customers;
	private User user;
	
	
	public Employee() {
		
	}

	public Employee(String id, String password, String firstname, String lastname, String bday, String address, int plz){
		super(id, password, firstname, lastname, bday, address, plz);
		this.customers = new ArrayList<Customer>();
	}

	public void addCustomer(Customer c) throws Exception {
		if (c.getAdviser() != null) {
			throw new Exception("Customer already has an adviser");
		}
		this.customers.add(c);
		c.setAdviser(this);
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public void removeCustomer(Customer c) throws Exception {
		if (!this.customers.contains(c) || c.getAdviser() != this) {
			throw new Exception("Employee is not the adviser of given user");
		}
		this.customers.remove(c);
		c.setAdviser(null);
	}

	public ArrayList<Customer> getCustomers() {
		return this.customers;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}
	
	
}
