package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.view.frames.JFrameAdapter;

public class ActionLogout extends AbstractActionAdapter {
	
	public ActionLogout(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Logout");
		putValue(SHORT_DESCRIPTION, "guess what happens ;)");
	}
}
