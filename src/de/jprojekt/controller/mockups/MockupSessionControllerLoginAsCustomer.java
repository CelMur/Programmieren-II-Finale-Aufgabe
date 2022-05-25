package de.jprojekt.controller.mockups;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.main.Launcher;

public class MockupSessionControllerLoginAsCustomer implements ISessionController{

	private ApplicationData data;
	
	public MockupSessionControllerLoginAsCustomer(ApplicationData data) {
		this.data = data;
	}
	
	@Override
	public boolean login(String username, String password) {
		data.setCurrentUser(new Customer());
		return true;
	}

	@Override
	public void logout() {
		//does nothing
	}

}
