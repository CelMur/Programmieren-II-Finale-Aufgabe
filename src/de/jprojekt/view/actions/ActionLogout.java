package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ActionLogout extends AbstractGuiAction {
	
	public ActionLogout(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Logout");
		putValue(SHORT_DESCRIPTION, "guess what happens ;)");
	}
}
