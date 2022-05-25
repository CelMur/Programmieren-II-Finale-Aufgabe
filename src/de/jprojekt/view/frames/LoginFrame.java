package de.jprojekt.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.jprojekt.view.actions.ActionLogin;

public class LoginFrame extends JFrame{
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JButton btnLogin;
	
	
	private final AbstractAction actLogin;
	
	public LoginFrame() {
		
		actLogin = new ActionLogin(this);
		
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
		
		btnLogin = new JButton(actLogin);
		
		bottomPanel.add(btnLogin);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		pack();
		
		
	}
}
