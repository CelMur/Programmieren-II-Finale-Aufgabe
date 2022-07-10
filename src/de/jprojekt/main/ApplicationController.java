package de.jprojekt.main;

import de.jprojekt.controller.BankAccountController;
import de.jprojekt.controller.CustomerController;
import de.jprojekt.controller.EmployeeController;
import de.jprojekt.controller.MySqlDbConnector;
import de.jprojekt.controller.SessionController;
import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.controller.interfaces.IDBConnector;
import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.controller.interfaces.IUserController;
import de.jprojekt.controller.mockups.MockupSessionControllerLoginAsCustomer;
import de.jprojekt.utils.BankingException;

import java.sql.SQLException;

public class ApplicationController {
	
	private ApplicationGui gui;
	private ApplicationData data;
	
	
	private ISessionController sessionController;
	private IDBConnector dbConnector;
	private ICustomerController customerController;
	private IEmployeeController employeeController;
	private IBankAccountController bankAccountController;
	
	
	
	private static ApplicationController instance;
	
	public static ApplicationController create() {
		if(instance == null) {
			instance = new ApplicationController();
		}
		
		return instance;
	}
	
	public static ApplicationController getInstance() {
		return instance;
	}
	
	
	
	private ApplicationController() {
		initializeController();
	}
	

	protected void initializeController() {
		//sessionController = new MockupSessionControllerLoginAsCustomer(data);
		
		dbConnector = new MySqlDbConnector();
		sessionController = new SessionController();
		customerController = new CustomerController();
		employeeController = new EmployeeController();
		bankAccountController = new BankAccountController();

	}
	
	
	/**
	 * wird vom Laucher aufgerufen kurz bevor das Login-Fenster angezeigt wird
	 * @throws BankingException 
	 */
	public void onLaunchLogin() throws BankingException {

       dbConnector.initConnection();
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
	
	public void setDBConnector(IDBConnector dbConnector) {
		this.dbConnector = dbConnector;
	}
	
	public IDBConnector getDBConnector() {
		return this.dbConnector;
	}
	
	public void setCustomerController(ICustomerController controller) {
		this.customerController = controller;
	}
	
	public void setEmployeeController(IEmployeeController controller) {
		this.employeeController = controller;
	}
	
	public ICustomerController getCustomerController() {
		return this.customerController;
	}

	public IEmployeeController getEmployeeController() {
		return this.employeeController;
	}
	public void setBankAccountController(IBankAccountController controller) {
		this.bankAccountController = controller;
	}
	public IBankAccountController getBankAccountController() {
		return this.bankAccountController;
	}
}
