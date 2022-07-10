package de.jprojekt.main;

import java.awt.Container;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;
import de.jprojekt.view.factories.MenuBarFactory;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JFrameLogin;
import de.jprojekt.view.frames.JFrameMain;
import de.jprojekt.view.frames.JPanelKontenUebersicht;
import de.jprojekt.view.panels.JPanelCustomerTable;

public class ApplicationGui {
	
	
	
	private static ApplicationGui instance;
	
	
	public static ApplicationGui create() {
		if(instance == null) {
			instance = new ApplicationGui();
		}
		return instance;
	}
	
	public static ApplicationGui getInstance() {
		return instance;
	}
	
	
	private ApplicationController controller;
	private ApplicationData data;
	
	private JFrameAdapter currentFrame;
	
	
	private ApplicationGui() {
		
	}
	
	/**
	 * Wird vom Launcher aufgerufen.
	 * Zeigt das Login-Fenster an
	 */
	public void onLaunchLogin() {
		cleanupCurrentFrame();
		showLoginFrame();
	}
	
	/**
	 * Wird vom Launcher aufgerufen.
	 * Zeigt das Haupt-Fenster an.
	 */
	public void onLaunchApplication() {
		cleanupCurrentFrame();
		showMainFrame();
		
		User u = data.getCurrentUser();
		
		if(u instanceof Employee) initializeEmployeeGui();
		if(u instanceof Customer) initializeCustomerGui();
	}
	
	
	private void cleanupCurrentFrame() {
		if(currentFrame != null) currentFrame.dispose();
	}
	
	private void showLoginFrame() {
		currentFrame = new JFrameLogin(this);
		currentFrame.setVisible(true);
	}
	
	private void showMainFrame() {
		currentFrame = new JFrameMain(this);
		currentFrame.setVisible(true);
		
	}
	
	
	public void initializeCustomerGui() {
		Container content = currentFrame.getContentPane();
		
		currentFrame.setJMenuBar(MenuBarFactory.createCustomerMenuBar(currentFrame));
		content.add(new JPanelKontenUebersicht());
	}
	
	public void initializeEmployeeGui() {
		Container content = currentFrame.getContentPane();
		
		currentFrame.setJMenuBar(MenuBarFactory.createEmployeeMenuBar(currentFrame));
		content.add(new JPanelCustomerTable());
		
	}

	public ApplicationController getApp() {
		return this.controller;
	}
	

	public void setController(ApplicationController controller) {
		this.controller = controller;
	}
	
	public JFrameAdapter getCurrentFrame() {
		return this.currentFrame;
	}

	public ApplicationData getData() {
		return data;
	}

	public void setData(ApplicationData data) {
		this.data = data;
	}
	
	
	
	
}
