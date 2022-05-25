package de.jprojekt.controller.mockups;

import de.jprojekt.controller.interfaces.ISessionController;

public class MockupSessionController implements ISessionController{

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void logout() {
		//does nothing
	}

}
