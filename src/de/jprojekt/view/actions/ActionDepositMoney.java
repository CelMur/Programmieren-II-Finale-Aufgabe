package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionDepositMoney extends AbstractActionAdapter{
	
	public ActionDepositMoney(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Geld einzahlen");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum Geldeinzahlen an");
	}
}
