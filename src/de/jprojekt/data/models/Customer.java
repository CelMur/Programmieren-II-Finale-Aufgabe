package de.jprojekt.data.models;
import java.util.ArrayList;
import java.util.Date; 


public class Customer extends User {
	private ArrayList<BankAccount> bankAccounts;
	private Employee adviser;
	private User user;

	public ArrayList<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public void addBankAccount(BankAccount bankAccount) {
		this.bankAccounts.add(bankAccount);
	}

	public Employee getAdviser() {
		return this.adviser;
	}

	public void setAdviser(Employee adviser) {
		this.adviser = adviser;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
