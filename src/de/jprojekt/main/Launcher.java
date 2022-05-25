package de.jprojekt.main;
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
	
	public static Launcher create(Gui gui, Application app) {
		if(instance == null) {
			instance = new Launcher(gui, app);
			return instance;
		}
		return null;
	}
	
	public static Launcher getInstance() {
		return instance; 
	}
	
	private Gui gui;
	private Application app;
	
	private Launcher(Gui gui, Application app) {
		this.gui = gui;
		this.app = app;
		
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
		
		
		app.onLaunchApplication();
		gui.onLaunchApplication();
	}
	
	
}
