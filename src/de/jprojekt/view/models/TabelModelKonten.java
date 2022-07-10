package de.jprojekt.view.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.jprojekt.data.models.BankAccount;

public class TabelModelKonten extends AbstractTableModel {
    private final List<BankAccount> kontoList;
     
	    private final String[] columnNames = new String[] {
	            "Kontotyp", "Vorname", "Nachname", "Balance"
	    };
	    private final Class[] columnClass = new Class[] {
	        Integer.class, String.class, String.class, Long.class
	    };
	 
	    public TabelModelKonten(List<BankAccount> kontoListe)
	    {
	        this.kontoList = kontoListe;
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
	        return kontoList.size();
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	        BankAccount row = kontoList.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getName();
	        }
	        else if(1 == columnIndex) {
	            return row.getCustomer().getFirstname();
	        }
            else if(2 == columnIndex) {
	            return row.getCustomer().getLastname();
	        }
	        else if(3 == columnIndex) {
	            return row.getBalance();
	        }
	        return null;
	    }
    
}
