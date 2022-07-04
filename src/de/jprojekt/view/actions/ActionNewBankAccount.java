package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionNewBankAccount extends AbstractActionAdapter{

	public ActionNewBankAccount(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Neues Konto er�ffnen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum er�ffnen eines neuen Kontos an");
	}
}
