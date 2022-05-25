package de.jprojekt.main;

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
	
	
	public void launchLogin() {
		app.onLaunchLogin();
		gui.onLaunchLogin();
		
	}
	
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
