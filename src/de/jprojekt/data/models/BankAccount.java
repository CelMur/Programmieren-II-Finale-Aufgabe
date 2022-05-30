package de.jprojekt.data.models;

public abstract class BankAccount {

	int accountid;
	String name;
	long balance;
	//String Type; 
	long maxDebt;
	int type;
	int customerid;
	int locked;
	
	public abstract boolean withdraw();
	
	public abstract boolean deposit();

	public long getMaxDebt() {
		return maxDebt;
	}

	public void setMaxDebt(long maxDebt) {
		this.maxDebt = maxDebt;
	}

	public String getName() {
		return name;
	}

	public long getBalance() {
		return balance;
	} 
	
	
}
