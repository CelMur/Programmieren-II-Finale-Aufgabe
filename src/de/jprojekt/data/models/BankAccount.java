package de.jprojekt.data.models;

public abstract class BankAccount {
	
	String name;
	long balance;
	long maxDebt;

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

}
