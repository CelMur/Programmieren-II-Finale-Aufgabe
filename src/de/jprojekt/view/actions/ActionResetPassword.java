package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionResetPassword extends AbstractActionAdapter{
	
	public ActionResetPassword(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Password zur�cksetzen");
		putValue(SHORT_DESCRIPTION, "zeigt den Dialog zum Zur�cksetzen des Login-Passworts an");
	}

}
