package de.jprojekt.view.actions;

import javax.swing.JFrame;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionChangePassword extends AbstractActionAdapter{
	
	public ActionChangePassword(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Password �ndern");
		putValue(SHORT_DESCRIPTION, "zeigt den Dialog zum �ndern des Login-Passworts an");
	}

}
