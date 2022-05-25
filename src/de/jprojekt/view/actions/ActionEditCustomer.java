package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionEditCustomer extends AbstractActionAdapter{

	public ActionEditCustomer(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Kude bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Kunden-Daten an");
	}
}
