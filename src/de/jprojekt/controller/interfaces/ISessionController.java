package de.jprojekt.controller.interfaces;

public interface ISessionController {
	/**
	 * wird von der Gui zum einloggen benötigt
	 * 
	 * @param username
	 * @param password
	 * @return true wenn der Login erfolgreich ist
	 */
	public boolean login(String username, String password);
	
	/**
	 * wird von der GUI
	 */
	public void logout();
}
