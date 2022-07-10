package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionChangePassword extends AbstractActionAdapter{
	
	public ActionChangePassword(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Password ändern");
		putValue(SHORT_DESCRIPTION, "zeigt den Dialog zum Ändern des Login-Passworts an");
	}

}
