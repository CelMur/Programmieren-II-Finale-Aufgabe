package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionResetPassword extends AbstractActionAdapter{
	
	public ActionResetPassword(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Password zurücksetzen");
		putValue(SHORT_DESCRIPTION, "zeigt den Dialog zum Zurücksetzen des Login-Passworts an");
	}

}
