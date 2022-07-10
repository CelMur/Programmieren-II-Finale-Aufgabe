package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JFrameNewCustomer;

public class ActionEditEmployee extends AbstractActionAdapter{
	
	public ActionEditEmployee(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Profil bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Banker-Daten an");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		ICustomerController controller = appController.getCustomerController();
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JDialog();//JFrameNewCustomer(frame, controller);
		
		
		dialog.setTitle("Neuer Kunde");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}
}
