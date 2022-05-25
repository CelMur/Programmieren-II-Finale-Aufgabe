package de.jprojekt.view.actions;

import javax.swing.JFrame;

public class ActionLogin extends AbstractActionAdapter{
	public ActionLogin(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Login");
		putValue(SHORT_DESCRIPTION, "its a Logig, ... nothing spezial :3");
	}
	
}
