package de.jprojekt.view.frames;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;

import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.DepositAccount;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.GiroAccount;
import de.jprojekt.data.models.SavingAccount;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.view.models.TabelModelKonten;

public class JPanelKontenUebersicht extends JPanel{
		
	private TabelModelKonten model;
	private JTable table;
	

	public JPanelKontenUebersicht() {
		
		initializeComponent();
	}
	
	private void initializeComponent() {
		
		model = new TabelModelKonten(initTestData());
		table = new JTable(model);
		
		setLayout(new BorderLayout());
		add(new JLabel("Konto-Übersicht:"), BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
	}
	
	/*
	public static void main(String[] args) {
		
		JFrame meinDialog = new JFrame();
		JPanel panel = new JPanelKontenUebersicht();
		meinDialog.add(panel);
		meinDialog.setTitle("Tabeltest");
		meinDialog.setSize(450, 300);
		meinDialog.setVisible(true);
	}
	*/
	
	public List<BankAccount> initTestData() {
		List<BankAccount> konten = new ArrayList<BankAccount>();
		
		Customer person = new Customer();
		person.setFirstname("Cynthia");
		person.setLastname("Winkler");
		person.setAddress("iwo in Seckenheim :)");
	
		konten.add(new GiroAccount("abc", person, "1"));
		konten.add(new DepositAccount("def", person, "2"));
		konten.add(new SavingAccount("ghi", person, "3"));
		konten.add(new SavingAccount("jkl", person, "4"));
		return konten;
	}
}
