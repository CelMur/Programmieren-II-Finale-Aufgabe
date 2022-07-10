package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.view.GuiMessages;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JDialogNewCustomer;
import de.jprojekt.view.frames.JDialogNewEmpolyee;

public class ActionNewEmployee extends AbstractActionAdapter{

	public ActionNewEmployee(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Banker anlegen");
		putValue(SHORT_DESCRIPTION, "öffnet den Dialog zum anlegen eines neuen Bankers");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		IEmployeeController controller = appController.getEmployeeController(); 
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JDialogNewEmpolyee(frame, controller);
		
		dialog.setTitle("Neuer Banker");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
	}
	
}
