package de.jprojekt.main;

import de.jprojekt.data.models.User;

public class ApplicationData {
	private User currentUser;
	
	
	
	private static ApplicationData instance;
	
	public static ApplicationData create() {
		if(instance == null) {
			instance = new ApplicationData();
		}
		return instance;
	}
	
	public static ApplicationData getInstance() {
		return instance;
	}

	
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
