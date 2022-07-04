package de.jprojekt.controller.mockups;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.data.models.Employee;
import de.jprojekt.main.ApplicationData;

public class MockupSessionControllerLoginAsEmployee implements ISessionController{

	private ApplicationData data;
	
	public MockupSessionControllerLoginAsEmployee(ApplicationData data) {
		this.data = data;
	}
	
	@Override
	public boolean login(String username, String password) {
		data.setCurrentUser(new Employee());

		return true;
	}

	
	@Override
	public void logout() {
		//does nothing
	}
}
