package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.data.models.Employee;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JFrameEditEmployee;

public class ActionEditEmployee extends AbstractActionAdapter{
	
	public ActionEditEmployee(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Profil bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Banker-Daten an");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		ApplicationData appData = ApplicationData.getInstance();
		
		IEmployeeController controller = appController.getEmployeeController();
		JFrameAdapter frame = getFrame();
		
		Employee currentUser = (Employee) appData.getCurrentUser();
		
		JDialog dialog = new JFrameEditEmployee(frame, controller, currentUser);
		
		dialog.setTitle("Eigenes Profil bearbeiten");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}
}
