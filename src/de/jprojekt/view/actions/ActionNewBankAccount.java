package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionNewBankAccount extends AbstractGuiAction{

	public ActionNewBankAccount(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Neues Konto eröffnen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum eröffnen eines neuen Kontos an");
	}
}
