package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionWithdrawMoney extends AbstractActionAdapter{
	
	public ActionWithdrawMoney(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Geld abheben");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum Geldabheben an");
	}
}
