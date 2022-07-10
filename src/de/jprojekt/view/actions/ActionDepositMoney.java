package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.view.frames.JDialogDepositMoney;
import de.jprojekt.view.frames.JDialogWithdrawMoney;
import de.jprojekt.view.frames.JFrameAdapter;

public class ActionDepositMoney extends AbstractActionAdapter{
	
	public ActionDepositMoney(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Geld einzahlen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum Geldeinzahlen an");
	}
	
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		IBankAccountController controller = appController.getBankAccountController();
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JDialogDepositMoney(frame, controller);
		
		
		dialog.setTitle("Geld Abheben");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}
}
