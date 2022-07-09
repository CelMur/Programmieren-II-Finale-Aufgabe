package de.jprojekt.view.factories;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import de.jprojekt.data.models.User;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.main.ApplicationGui;
import de.jprojekt.view.CustomerMenuBar;
import de.jprojekt.view.EmployeeMenuBar;
import de.jprojekt.view.frames.JFrameAdapter;

public class MenuBarFactory {
	
	public static EmployeeMenuBar createEmployeeMenuBar(JFrameAdapter frame) {
		ApplicationGui gui = ApplicationGui.getInstance();
		
		return new EmployeeMenuBar(gui, frame);
	}
	
	public static CustomerMenuBar createCustomerMenuBar(JFrameAdapter frame) {
		ApplicationGui gui = ApplicationGui.getInstance();
		
		return new CustomerMenuBar(gui, frame);
	}
}
