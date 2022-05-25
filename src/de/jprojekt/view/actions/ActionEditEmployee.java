package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionEditEmployee extends AbstractGuiAction{
	
	public ActionEditEmployee(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Profiel bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Banker-Daten an");
	}
}
