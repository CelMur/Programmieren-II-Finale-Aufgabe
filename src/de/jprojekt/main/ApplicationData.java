package de.jprojekt.main;

import de.jprojekt.data.models.User;

public class ApplicationData {
	private User currentUser;

	
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
