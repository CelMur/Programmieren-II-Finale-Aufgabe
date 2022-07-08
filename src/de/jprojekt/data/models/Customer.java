package de.jprojekt.data.models;
import java.util.ArrayList;
import java.util.Date; 


public class Customer extends User {
	private ArrayList<BankAccount> bankAccounts;
	private Employee adviser;

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
	
	public Customer() {}
	
	public Customer(String id, String password, String firstname, String lastname, Date bday, String address, int plz){
		super(id, password, firstname, lastname, bday, address, plz);
		this.bankAccounts = new ArrayList<BankAccount>();
	}

}
