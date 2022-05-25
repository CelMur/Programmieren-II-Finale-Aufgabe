package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.main.Gui;
import de.jprojekt.main.Launcher;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.models.LoginData;

public class ActionLogin extends AbstractActionAdapter{
	
	private ISessionController session;
	
	private Gui gui;
	
	public ActionLogin(Gui gui, JFrameAdapter frame) {
		super(frame);
		
		this.gui = gui;
		
		session = gui.getApp().getSessionController();
		
		putValue(NAME, "Login");
		putValue(SHORT_DESCRIPTION, "its a Logig, ... nothing spezial :3");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFrameAdapter frame = getFrame();
		Object obj = frame.getData();
		LoginData data = null;
		
		if(obj instanceof LoginData) data = (LoginData) obj;
			
		if(data == null) throw new NullPointerException("ActionLogin: expected LoginData is null");
		

		if(session.login(data.getUsername(), data.getPassword())) {
			Launcher.getInstance().launchApplication();
		}
	}
	
}
