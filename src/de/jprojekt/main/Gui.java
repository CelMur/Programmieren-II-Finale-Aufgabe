package de.jprojekt.main;

import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.LoginFrame;
import de.jprojekt.view.frames.MainFrame;

public class Gui {
	
	private Application app;
	
	private JFrameAdapter currentFrame;
	
	
	public Gui() {
	
	}
	
	 
	public void onLaunchLogin() {
		cleanupCurrentFrame();
		showLoginFrame();
	}
	
	
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
	
	
	public void setupCustomerGui() {
		
	}
	
	public void setupEmployeeGui() {
		
	}

	public Application getApp() {
		return this.app;
	}
	

	public void setApp(Application app) {
		this.app = app;
	}
	
	public JFrameAdapter getCurrentFrame() {
		return this.currentFrame;
	}
	
	
}
