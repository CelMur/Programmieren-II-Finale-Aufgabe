package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionResetPassword extends AbstractActionAdapter{
	
	public ActionResetPassword(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Password zur�cksetzen");
		putValue(SHORT_DESCRIPTION, "zeigt den Dialog zum Zur�cksetzen des Login-Passworts an");
	}

}
