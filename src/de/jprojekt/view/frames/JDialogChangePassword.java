package de.jprojekt.view.frames;
  
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class JDialogChangePassword extends JFrame{ 
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtReapeatNewPassword;
	private JButton btnChangePassword;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JPanel up;
	private JPanel lp;

	public JDialogChangePassword(){
		super("Passwort ändern");
		setLayout(new FlowLayout());
		
		up = new JPanel();
		up.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JLabel label1 = new JLabel("Altes Passwort");
		add (label1);		
		txtOldPassword =  new JPasswordField(15);
		add (txtOldPassword);
		
		
		lp = new JPanel();
		lp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JLabel label2 = new JLabel("Neues Passwort");
		add (label2);
		
		txtNewPassword = new JPasswordField(15);
		add (txtNewPassword);
		
		JLabel label3 = new JLabel("Neues Passwort wiederholen");
		add (label3);
		
		txtReapeatNewPassword = new JPasswordField(15);
		add (txtReapeatNewPassword);
		
		btnChangePassword = new JButton("Passwort ändern");
		add(btnChangePassword);
		
		
		
		PasswordHandler handler = new PasswordHandler();
		txtOldPassword.addActionListener(handler);
		txtNewPassword.addActionListener(handler);
		txtReapeatNewPassword.addActionListener(handler);
		btnChangePassword.addActionListener(handler);
	}
	
	private class PasswordHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String string = "";
						
		if (event.getSource()==btnChangePassword){
			String strOldPW = new String(txtOldPassword.getPassword());
				if (strOldPW.equals("test")){
					//TEST ENTFERNEN UND DURCH PARAMETER ERSETZEN !!!
					string=String.format("txtReapeatNewPassword: %s", event.getActionCommand());
					String strReNewPW = new String(txtReapeatNewPassword.getPassword());
					string=String.format("txtNewPassword: %s", event.getActionCommand());
					String strNewPW = new String(txtNewPassword.getPassword());
					 if (strNewPW.equals(strReNewPW)){
						//PASSWORTVARIABEL = strNewPW
						JOptionPane.showMessageDialog(null, "Passwort geändert!");}}
					else if (strOldPW != "test"){
						//TEST ENTFERNEN UND DURCH PARAMETER ERSETZEN !!!
						JOptionPane.showMessageDialog(null, "Passwort ist nicht korrekt!");}}

			
				
		}
	}
}
