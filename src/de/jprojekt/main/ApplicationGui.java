package de.jprojekt.main;

import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.LoginFrame;
import de.jprojekt.view.frames.MainFrame;

public class ApplicationGui {
	
	private ApplicationController controller;
	private ApplicationData data;
	
	private JFrameAdapter currentFrame;
	
	
	public ApplicationGui() {
	
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
	}
	
	
	private void cleanupCurrentFrame() {
		if(currentFrame != null) currentFrame.dispose();
	}
	
	private void showLoginFrame() {

		currentFrame = new LoginFrame(this);
		currentFrame.setVisible(true);
	}
	
	private void showMainFrame() {

		currentFrame = new MainFrame(this);
		currentFrame.setVisible(true);
	}
	
	
	public void initializeCustomerGui() {
		
	}
	
	public void initializeEmployeeGui() {
		
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
