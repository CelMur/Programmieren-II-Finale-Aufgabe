package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JFrameMoneyTransfer;
import de.jprojekt.view.frames.JDialogNewCustomer;

public class ActionTransferMoney extends AbstractActionAdapter{

	public ActionTransferMoney(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Geld überweisen");
		putValue(SHORT_DESCRIPTION, "Öffnet einen Dialog zum Durchführen einer Überweisung");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		IBankAccountController controller = appController.getBankAccountController();
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JFrameMoneyTransfer(getFrame(), controller);
		
		
		dialog.setTitle("Geld Überweisen");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
	}

}
