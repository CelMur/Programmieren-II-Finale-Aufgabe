package de.jprojekt.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.data.models.User;
import de.jprojekt.main.Gui;
import de.jprojekt.view.actions.ActionLogin;
import de.jprojekt.view.models.LoginData;

public class LoginFrame extends JFrameAdapter{
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JButton btnLogin;
	
	private Gui gui;
	private ISessionController session;
	
	private final AbstractAction actLogin;
		
	public LoginFrame(Gui gui) {
		this.gui = gui;
		
		session = gui.getApp().getSessionController();
		
		actLogin = new ActionLogin(gui, this);
		
		initialize();
	}
	
	
	
	private void initialize() {
		
		setLayout(new BorderLayout(5,5));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,2,5,5));

		
		JLabel lblUsername = new JLabel("Username");
		fieldUsername = new JTextField(10);
		
		JLabel lblPassword = new JLabel("Password");
		fieldPassword = new JPasswordField();
		
		centerPanel.add(lblUsername);	
		centerPanel.add(fieldUsername);
		centerPanel.add(lblPassword);
		centerPanel.add(fieldPassword);
		
		add(centerPanel, BorderLayout.CENTER);
		
		
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		
		btnLogin = new JButton("Login");
		btnLogin.setAction(actLogin);
		
		bottomPanel.add(btnLogin);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		pack();
		
		
	}



	@Override
	public Object getData() {
		return new LoginData(fieldUsername.getText(), new String(fieldPassword.getPassword()));
		
	}
}
