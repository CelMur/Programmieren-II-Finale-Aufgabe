package de.jprojekt.data.models;

public abstract class BankAccount {
	private int id;
	private String name;
	private long balance;
	private long maxDebt;
	private Customer customer;
	private boolean locked;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBalance() {
		return this.balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getMaxDebt() {
		return this.maxDebt;
	}

	public void setMaxDebt(long maxDebt) {
		this.maxDebt = maxDebt;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isLocked() {
		return this.locked;
	}

	public boolean getLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
