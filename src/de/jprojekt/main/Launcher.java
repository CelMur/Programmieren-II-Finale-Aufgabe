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
 *die daf�r zust�ndig sind, dass alle f�r die Phasen wichtigen initialisierungen
 *durchgegf�hrt werden.
 *}
 */
public class Launcher {
	
	private static Launcher instance;
	
	public static Launcher create(ApplicationGui gui, Application app, ApplicationData data) {
		if(instance == null) {
			instance = new Launcher(gui, app, data);
			return instance;
		}
		return null;
	}
	
	public static Launcher getInstance() {
		return instance; 
	}
	
	
	
	private ApplicationGui gui;
	private Application app;
	private ApplicationData data;
	
	
	
	private Launcher(ApplicationGui gui, Application app, ApplicationData data) {
		this.gui = gui;
		this.app = app;
		this.data = data;
		
		throwExceptionIfGuiIsMissing();
		throwExceptionIfApplicationIsMissing();
		
		gui.setApp(app);
		app.setGui(gui);
		
	}
	
	private void throwExceptionIfGuiIsMissing() {
		if(this.gui == null) throw new NullPointerException("Launcher is missing the GUI component");
	}
	
	private void throwExceptionIfApplicationIsMissing() {
		if(this.app == null) throw new NullPointerException("Launcher is missing the Application component");
	}
	
	
	/**
	 * Ruft das Login-Fenster
	 */
	public void launchLogin() {
		app.onLaunchLogin();
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
			
		
		app.onLaunchApplication();
		gui.onLaunchApplication();
	}
	
	
}
