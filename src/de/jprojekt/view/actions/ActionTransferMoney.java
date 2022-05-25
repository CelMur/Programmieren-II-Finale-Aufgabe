package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionTransferMoney extends AbstractActionAdapter{

	public ActionTransferMoney(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Geld überweisen");
		putValue(SHORT_DESCRIPTION, "Öffnet einen Dialog zum Durchführen einer Überweisung");
	}
}
