package de.jprojekt.controller;

import java.sql.SQLException;

import de.jprojekt.controller.interfaces.IDBConnector;
import de.jprojekt.utils.mysql.Mysql;

public class MySqlDbConnector implements IDBConnector{

	@Override
	public void initConnection() {
		// TODO Auto-generated method stub
		 try {
	            Mysql.createDB();
				Mysql.testClass();
				System.out.println("Datenbank erfolgreich initialisiert.");
	        } catch (Exception e) {
	        	System.out.println("Datenbank-Initialisierung fehlerhaft.\n");
	            e.printStackTrace();
	        }	       
	}

}
