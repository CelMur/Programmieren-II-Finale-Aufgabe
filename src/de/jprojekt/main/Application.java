package de.jprojekt.main;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.controller.mockups.MockupSessionController;

public class Application {
	
	protected Gui gui;
	
	
	protected ISessionController sessionController;
	
	public Application() {
		initializeController();
	}
	
	

	protected void initializeController() {
		sessionController = new MockupSessionController();
	}
	
	
	
	public void onLaunchLogin() {
		throwExeptionIfGuiIsMissing();
		
	}
	
	public void onLaunchApplication() {
		throwExeptionIfGuiIsMissing();
		
	}
	
	
	protected void  throwExeptionIfGuiIsMissing() {
		if(gui == null) throw new NullPointerException("missing GUI component in Application");
	}
	

	
	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public ISessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(ISessionController sessionController) {
		this.sessionController = sessionController;
	}
}
