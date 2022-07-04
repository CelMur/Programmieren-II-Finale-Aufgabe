package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionEditCustomer extends AbstractActionAdapter{

	public ActionEditCustomer(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Kude bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Kunden-Daten an");
	}
}
