package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JFrameNewCustomer;
import de.jprojekt.view.panels.CustomerDataPanel;

public class ActionNewCustomer extends AbstractActionAdapter{
	
	public ActionNewCustomer(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Kunde anlegen");
		putValue(SHORT_DESCRIPTION, "öffnet den Dialog zum anlegen eines neuen Kunden");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		ICustomerController controller = appController.getCustomerController();
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JFrameNewCustomer(frame, controller);
		
		
		dialog.setTitle("Neuer Kunde");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}

}
