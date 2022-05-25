package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionTransferMoney extends AbstractActionAdapter{

	public ActionTransferMoney(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Geld �berweisen");
		putValue(SHORT_DESCRIPTION, "�ffnet einen Dialog zum Durchf�hren einer �berweisung");
	}
}
