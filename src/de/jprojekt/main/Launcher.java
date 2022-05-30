package de.jprojekt.main;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;

/**
 * 
 * @author luis.eckert
 *
 *
 *{@summary 
 *Der Launcher kapselt das Start-Verhalten der Anwendung 
 *und teilt es in zwei Phasen: Login und Haupt-Anwendung(Application)
 *
 *Beide Phasen haben jeweils eine Funktion (launchLogin und launchApplication),
 *die dafür zuständig sind, dass alle für die Phasen wichtigen initialisierungen
 *durchgegführt werden.
 *}
 */
public class Launcher {
	
	private static Launcher instance;
	
	public static Launcher create(ApplicationGui gui, ApplicationController controller, ApplicationData data) {
		if(instance == null) {
			instance = new Launcher(gui, controller, data);
			return instance;
		}
		return null;
	}
	
	public static Launcher getInstance() {
		return instance; 
	}
	
	
	
	private ApplicationGui gui;
	private ApplicationController controller;
	private ApplicationData data;
	
	
	
	private Launcher(ApplicationGui gui, ApplicationController controller, ApplicationData data) {
		this.gui = gui;
		this.controller = controller;
		this.data = data;
		
		throwExceptionIfGuiIsMissing();
		throwExceptionIfApplicationIsMissing();
		
		gui.setController(controller);
		controller.setGui(gui);
		
	}
	
	private void throwExceptionIfGuiIsMissing() {
		if(this.gui == null) throw new NullPointerException("Launcher is missing the GUI component");
	}
	
	private void throwExceptionIfApplicationIsMissing() {
		if(this.controller == null) throw new NullPointerException("Launcher is missing the ApplicationController component");
	}
	
	
	/**
	 * Ruft das Login-Fenster
	 */
	public void launchLogin() {
		controller.onLaunchLogin();
		gui.onLaunchLogin();
		
	}
	
	/**
	 * Initialisiert die Gui je nach eingeloggtem User (Customer oder Employee)) und 
	 * zeigt das Haupt-Fenster an.
	 */
	public void launchApplication() {
		
		
		/*TODO: Unterscheidung des "User" Typs
		 * 
		 * Fall 1:	Typ = Customer -> gui.setupCustomerGui()
		 * 
		 * Fall 2:	Typ = Employee -> gui.setupEmployeeGui()
		 * 
		 * 
		 */
		
		User u = data.getCurrentUser();
		
		if(u instanceof Customer) gui.initializeCustomerGui();
		if(u instanceof Employee) gui.initializeEmployeeGui();
			
		
		controller.onLaunchApplication();
		gui.onLaunchApplication();
	}
	
	
}
