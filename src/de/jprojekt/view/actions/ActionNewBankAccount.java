package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.view.frames.JDialogNewBankAccount;
import de.jprojekt.view.frames.JFrameAdapter;

public class ActionNewBankAccount extends AbstractActionAdapter{

	public ActionNewBankAccount(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Neues Konto eröffnen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum eröffnen eines neuen Kontos an");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		IBankAccountController controller = appController.getBankAccountController();
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JDialogNewBankAccount(frame, controller);
		
		
		dialog.setTitle("Neuer Kunde");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}
}
