package de.jprojekt.view.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.jprojekt.data.models.Employee;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.view.models.TableModelCustomer;

public class JPanelCustomerTable extends JPanel{
	
	private ApplicationData appData; 
	
	private TableModelCustomer model;
	private JTable table;
	

	public JPanelCustomerTable() {
		appData = ApplicationData.getInstance();
		
		initializeComponent();
		initializeValues();
	}
	
	private void initializeComponent() {
		Employee currentUser = (Employee) appData.getCurrentUser();
		
		model = new TableModelCustomer(currentUser.getCustomers());
		table = new JTable(model);
		
		setLayout(new BorderLayout());
		add(new JLabel("Zugeordnete Kunden:"), BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
	}
	
	private void initializeValues() {
		
	}
}
