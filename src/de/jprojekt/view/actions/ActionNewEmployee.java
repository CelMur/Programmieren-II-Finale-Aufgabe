package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.view.GuiMessages;

public class ActionNewEmployee extends AbstractGuiAction{

	public ActionNewEmployee(JFrame frame) {
		super(frame);
		
		putValue(NAME, "Banker anlegen");
		putValue(SHORT_DESCRIPTION, "öffnet den Dialog zum anlegen eines neuen Bankers");
	}
	
}
