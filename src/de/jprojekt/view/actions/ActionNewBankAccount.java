package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionNewBankAccount extends AbstractActionAdapter{

	public ActionNewBankAccount(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Neues Konto eröffnen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum eröffnen eines neuen Kontos an");
	}
}
