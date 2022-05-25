package de.jprojekt.controller.interfaces;

public interface ISessionController {
	public boolean login(String username, String password);
	public void logout();
}
