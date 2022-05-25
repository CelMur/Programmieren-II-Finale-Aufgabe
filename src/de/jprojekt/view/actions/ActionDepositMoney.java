package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionDepositMoney extends AbstractGuiAction{
	
	public ActionDepositMoney(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Geld einzahlen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum Geldeinzahlen an");
	}
}
