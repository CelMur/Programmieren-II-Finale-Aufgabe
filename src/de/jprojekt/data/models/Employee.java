package de.jprojekt.data.models;

import java.util.ArrayList;

public class Employee extends User {
	private ArrayList<Customer> customers;
	private User user;

	public void addCustomer(Customer c) throws Exception {
		if (c.getAdviser() != null) {
			throw new Exception("Customer already has an adviser");
		}
		this.customers.add(c);
		c.setAdviser(this);
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
