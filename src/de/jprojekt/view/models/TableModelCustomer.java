package de.jprojekt.view.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.jprojekt.data.models.Customer;

public class TableModelCustomer extends AbstractTableModel{
	
	 private final List<Customer> customerList;
     
	    private final String[] columnNames = new String[] {
	            "Id", "Vorname", "Nachname"
	    };
	    private final Class[] columnClass = new Class[] {
	        Integer.class, String.class, String.class
	    };
	 
	    public TableModelCustomer(List<Customer> studentList)
	    {
	        this.customerList = studentList;
	    }
	     
	    @Override
	    public String getColumnName(int column)
	    {
	        return columnNames[column];
	    }
	 
	    @Override
	    public Class<?> getColumnClass(int columnIndex)
	    {
	        return columnClass[columnIndex];
	    }
	 
	    @Override
	    public int getColumnCount()
	    {
	        return columnNames.length;
	    }
	 
	    @Override
	    public int getRowCount()
	    {
	        return customerList.size();
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	        Customer row = customerList.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getId();
	        }
	        else if(1 == columnIndex) {
	            return row.getFirstname();
	        }
	        else if(2 == columnIndex) {
	            return row.getLastname();
	        }
	        return null;
	    }
	}