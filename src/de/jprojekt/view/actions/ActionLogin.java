package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.main.ApplicationGui;
import de.jprojekt.main.Launcher;
import de.jprojekt.utils.BankingException;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.models.LoginData;

public class ActionLogin extends AbstractActionAdapter{
	
	private ISessionController session;
	
	private ApplicationGui gui;
	
	public ActionLogin(ApplicationGui gui, JFrameAdapter frame) {
		super(frame);
		
		this.gui = gui;
		
		session = gui.getApp().getSessionController();
		
		putValue(NAME, "Login");
		putValue(SHORT_DESCRIPTION, "its a Logig, ... nothing spezial :3");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = getFrame().getData();
		LoginData data = null;
		
		if(obj instanceof LoginData) data = (LoginData) obj;
			
		if(data == null) throw new NullPointerException("ActionLogin: expected LoginData is null");
		
		try {
			if(session.login(data.getUsername(), data.getPassword())) 
				Launcher.getInstance().launchApplication();
			else
				JOptionPane.showMessageDialog(getFrame(), "Login Fehlgeschlagen.");
		
		}catch(BankingException ex) {
			JOptionPane.showMessageDialog(getFrame(), ex.getMessage());
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(getFrame(), "Login Fehlgeschlagen.");
		}
		
	}
	
}
