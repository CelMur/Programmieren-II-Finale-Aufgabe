package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionNewBankAccount extends AbstractGuiAction{

	public ActionNewBankAccount(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Neues Konto er�ffnen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum er�ffnen eines neuen Kontos an");
	}
}
