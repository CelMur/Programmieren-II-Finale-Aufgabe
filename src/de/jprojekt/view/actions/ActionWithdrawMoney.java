package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionWithdrawMoney extends AbstractGuiAction{
	
	public ActionWithdrawMoney(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Geld abheben");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum Geldabheben an");
	}
}
