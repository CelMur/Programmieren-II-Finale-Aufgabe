package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.view.GuiMessages;
import de.jprojekt.view.frames.JFrameAdapter;

public class ActionNewEmployee extends AbstractActionAdapter{

	public ActionNewEmployee(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Banker anlegen");
		putValue(SHORT_DESCRIPTION, "öffnet den Dialog zum anlegen eines neuen Bankers");
	}
	
}
