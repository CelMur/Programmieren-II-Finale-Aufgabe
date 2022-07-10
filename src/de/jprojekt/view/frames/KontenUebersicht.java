package de.jprojekt.view.frames;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.DepositAccount;
import de.jprojekt.data.models.GiroAccount;
import de.jprojekt.data.models.SavingAccount;

public class KontenUebersicht extends JFrame{
	
	private List<BankAccount> konten;
	
	//fuer tests 
	public KontenUebersicht() {
		initTestData();
	}
	
	public KontenUebersicht(List<BankAccount> pKonten) {
		this.konten = pKonten;
	}
	
	public void setKonten(List<BankAccount> pKonten) {
		this.konten = pKonten;
	}
	
	public void initTestData() {
		konten = new ArrayList<BankAccount>();
		
		Customer person = new Customer();
		person.setFirstname("Cynthia");
		person.setLastname("Winkler");
		person.setAddress("iwo in Seckenheim :)");
	
		konten.add(new GiroAccount("abc", person, ""));
		konten.add(new DepositAccount("def", person, ""));
		konten.add(new SavingAccount("ghi", person, ""));
		konten.add(new SavingAccount("jkl", person, ""));
	}
}
