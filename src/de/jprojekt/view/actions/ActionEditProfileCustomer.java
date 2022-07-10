package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JDialogEditCustomer;
import de.jprojekt.view.frames.JDialogEditEmployee;

public class ActionEditProfileCustomer extends AbstractActionAdapter{
	
	public ActionEditProfileCustomer(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Profil bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Banker-Daten an");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		ApplicationData appData = ApplicationData.getInstance();
		
		ICustomerController controller = appController.getCustomerController();
		JFrameAdapter frame = getFrame();
		
		Customer currentUser = (Customer) appData.getCurrentUser();
		
		JDialog dialog = new JDialogEditCustomer(frame, controller, currentUser);
		
		dialog.setTitle("Eigenes Profil bearbeiten");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}
}
