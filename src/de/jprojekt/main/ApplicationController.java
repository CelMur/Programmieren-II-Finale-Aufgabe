package de.jprojekt.main;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsCustomer;
import de.jprojekt.utils.Mysql;

import java.sql.SQLException;

public class ApplicationController {
	
	protected ApplicationGui gui;
	protected ApplicationData data;
	
	
	protected ISessionController sessionController;
	
	public ApplicationController() {
		initializeController();
	}
	

	protected void initializeController() {
		//sessionController = new MockupSessionControllerLoginAsCustomer(data);
	}
	
	
	/**
	 * wird vom Laucher aufgerufen kurz bevor das Login-Fenster angezeigt wird
	 */
	public void onLaunchLogin() {

        try {
            Mysql.createDB();
			Mysql.testClass();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Datenbank erfolgreich initialisiert.");

		throwExeptionIfGuiIsMissing();
	}
	/**
	 * Wird vom Launcher aufgerufen, kurz bevor das Haupt-Fenster angezeigt wird
	 */
	public void onLaunchApplication() {
		throwExeptionIfGuiIsMissing();
		
	}
	
	
	protected void  throwExeptionIfGuiIsMissing() {
		if(gui == null) throw new NullPointerException("missing GUI component in ApplicationController");
	}
	
	
	public void setData(ApplicationData data) {
		this.data = data;
	}
	
	public ApplicationData getData() {
		return this.data;
	}
	
	public void setGui(ApplicationGui gui) {
		this.gui = gui;
	}

	public ISessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(ISessionController sessionController) {
		this.sessionController = sessionController;
	}
}
