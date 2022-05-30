package de.jprojekt.main;

import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsEmployee;

public class Main {

    public static void main(String[] args) {

    	ApplicationData data = new ApplicationData();
    	ApplicationController app = new ApplicationController();
    	ApplicationGui gui = new ApplicationGui();
    	
    	//Mockup-Login-As-Customer
    	
    	//app.setSessionController(new MockupSessionControllerLoginAsCustomer(data));
    	
    	
    	
    	//Mockup-Login-As-Employee
    	
    	app.setSessionController(new MockupSessionControllerLoginAsEmployee(data));
    	
    	
    	Launcher l = Launcher.create(gui, app, data);
    	l.launchLogin();
    	
    }

}
