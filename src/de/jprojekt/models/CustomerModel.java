package de.jprojekt.models;

import java.time.LocalDate;
import java.util.List;

public class CustomerModel {
	private String name;
	private String lastNameString;
	private LocalDate birthday;
	private String address;
	private String telNumber;
	
	private List<BankAccountModel> bankAccounts;
	private UserModel user;
	private EmployeeModel adviser;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastNameString() {
		return lastNameString;
	}
	public void setLastNameString(String lastNameString) {
		this.lastNameString = lastNameString;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public List<BankAccountModel> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<BankAccountModel> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public EmployeeModel getAdviser() {
		return adviser;
	}
	public void setAdviser(EmployeeModel adviser) {
		this.adviser = adviser;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
}
