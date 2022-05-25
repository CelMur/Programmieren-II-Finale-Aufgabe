package de.jprojekt.main;

import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsCustomer;
import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsEmployee;

public class Main {

    public static void main(String[] args) {
    	
    	/*
        try {
            Mysql.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Test4");
        */
    	ApplicationData data = new ApplicationData();
    	Application app = new Application();
    	ApplicationGui gui = new ApplicationGui();
    	
    	//Mockup-Login-As-Customer
    	
    	//app.setSessionController(new MockupSessionControllerLoginAsCustomer(data));
    	
    	
    	
    	//Mockup-Login-As-Employee
    	
    	app.setSessionController(new MockupSessionControllerLoginAsEmployee(data));
    	
    	
    	Launcher l = Launcher.create(gui, app, data);
    	l.launchLogin();
    	
    	
    	
    }

}
