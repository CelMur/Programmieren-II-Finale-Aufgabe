package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.User;

public interface ISessionController {
	/**
	 * wird von der Gui zum einloggen ben�tigt
	 * 
	 * @param username
	 * @param password
	 * @return true wenn der Login erfolgreich ist
	 */
	public boolean login(String username, String password)throws Exception;
	
	/**
	 * wird von der GUI
	 */
	public void logout();
}
