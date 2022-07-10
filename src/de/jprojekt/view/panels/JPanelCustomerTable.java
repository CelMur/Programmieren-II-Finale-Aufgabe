package de.jprojekt.view.panels;

import javax.swing.JPanel;
import javax.swing.JTable;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.view.models.TableModelCustomer;

public class JPanelCustomerTable extends JPanel{
	
	
	IEmployeeController controller;
	
	
	TableModelCustomer tabModelCustomer;
	JTable table;
	

	public JPanelCustomerTable() {
		
	}
}
