package de.jprojekt.data.models;

public abstract class BankAccount {
	
	String name;
	long balance;
	//String Type; 
	long maxDebt;
	
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
