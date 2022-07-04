package de.jprojekt.main;

public class ApplicationContext {
	private ApplicationGui gui;
	private ApplicationData data;
	private ApplicationController controller;
	
	public ApplicationContext(ApplicationGui gui, ApplicationController controller, ApplicationData data) {
		this.controller = controller;
		this.data = data;
		this.gui = gui;
		
		throwExceptionIfGuiIsMissing();
		throwExceptionIfApplicationIsMissing();
		
		gui.setController(controller);
		controller.setGui(gui);	
	}
	
	

	private void throwExceptionIfGuiIsMissing() {
		if(this.gui == null) throw new NullPointerException("Launcher is missing the GUI component");
	}
	
	private void throwExceptionIfApplicationIsMissing() {
		if(this.controller == null) throw new NullPointerException("Launcher is missing the ApplicationController component");
	}
	
	
	
	
	public ApplicationGui getGui() {
		return this.gui;
	}
	
	public ApplicationController getController() {
		return this.controller;
	}
	
	public ApplicationData getData() {
		return this.data;
	}
}
