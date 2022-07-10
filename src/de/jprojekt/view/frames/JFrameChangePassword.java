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

public class JFrameChangePassword extends JFrame{ 
	private JPasswordField OldPW;
	private JPasswordField newPW;
	private JPasswordField reNewPW;
	private JButton change;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JPanel up;
	private JPanel lp;

	public JFrameChangePassword(){
		super("Passwort ändern");
		setLayout(new FlowLayout());
		
		up = new JPanel();
		up.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JLabel label1 = new JLabel("Altes Passwort");
		add (label1);		
		OldPW =  new JPasswordField(15);
		add (OldPW);
		
		
		lp = new JPanel();
		lp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JLabel label2 = new JLabel("Neues Passwort");
		add (label2);
		
		newPW = new JPasswordField(15);
		add (newPW);
		
		JLabel label3 = new JLabel("Neues Passwort wiederholen");
		add (label3);
		
		reNewPW = new JPasswordField(15);
		add (reNewPW);
		
		change = new JButton("Passwort ändern");
		add(change);
		
		
		
		PasswordHandler handler = new PasswordHandler();
		OldPW.addActionListener(handler);
		newPW.addActionListener(handler);
		reNewPW.addActionListener(handler);
		change.addActionListener(handler);
	}
	
	private class PasswordHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String string = "";
						
		if (event.getSource()==change){
			String strOldPW = new String(OldPW.getPassword());
				if (strOldPW.equals("test")){
					//TEST ENTFERNEN UND DURCH PARAMETER ERSETZEN !!!
					string=String.format("reNewPW: %s", event.getActionCommand());
					String strReNewPW = new String(reNewPW.getPassword());
					string=String.format("newPW: %s", event.getActionCommand());
					String strNewPW = new String(newPW.getPassword());
					 if (strNewPW.equals(strReNewPW)){
						//PASSWORTVARIABEL = strNewPW
						JOptionPane.showMessageDialog(null, "Passwort geändert!");}}
					else if (strOldPW != "test"){
						//TEST ENTFERNEN UND DURCH PARAMETER ERSETZEN !!!
						JOptionPane.showMessageDialog(null, "Passwort ist nicht korrekt!");}}

			
				
		}
	}
}
