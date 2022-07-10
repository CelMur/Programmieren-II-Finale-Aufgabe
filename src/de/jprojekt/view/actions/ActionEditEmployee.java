package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionEditEmployee extends AbstractActionAdapter{
	
	public ActionEditEmployee(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Profil bearbeiten");
		putValue(SHORT_DESCRIPTION, "zeigt einen Dialog zum bearbeiten der Banker-Daten an");
	}
}
