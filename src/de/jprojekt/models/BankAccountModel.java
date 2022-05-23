package de.jprojekt.models;

public class BankAccountModel {
	
	private String name;
	private long balance;
	private long maxDepth;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(long maxDepth) {
		this.maxDepth = maxDepth;
	}
	
}
