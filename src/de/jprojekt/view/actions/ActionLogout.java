package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.main.ApplicationGui;
import de.jprojekt.view.GuiMessages;
import de.jprojekt.view.frames.JFrameAdapter;

public class ActionLogout extends AbstractActionAdapter {
	
	private ApplicationGui gui;
	private ISessionController session; 
	
	public ActionLogout(ApplicationGui gui, JFrameAdapter frame) {
		super(frame);
		
		this.gui = gui;
		
		this.session = gui.getApp().getSessionController();
		
		putValue(NAME, "Logout");
		putValue(SHORT_DESCRIPTION, "guess what happens ;)");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		session.logout();
		getFrame().dispose();
	}
}
