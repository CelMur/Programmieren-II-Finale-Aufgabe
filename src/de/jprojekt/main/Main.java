package de.jprojekt.main;

import de.jprojekt.controller.MySqlDbConnector;
import de.jprojekt.controller.interfaces.IDBConnector;
import de.jprojekt.controller.mockups.MockupDBConnector;
import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsCustomer;
import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsEmployee;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

    	ApplicationData data = ApplicationData.create();
    	ApplicationController app = ApplicationController.create();
    	ApplicationGui gui = ApplicationGui.create();
    	
    	//Mockup-Login-As-Customer
    	//app.setDBConnector(new MySqlDbConnector());
    	app.setSessionController(new MockupSessionControllerLoginAsCustomer(data));
    	
    	
    	
    	//Mockup-Login-As-Employee
    	//app.setDBConnector(new MockupDBConnector());
    	//app.setSessionController(new MockupSessionControllerLoginAsEmployee(data));

    	Launcher l = Launcher.create(gui, app, data);
    	l.launchLogin();
    	
    }

}
