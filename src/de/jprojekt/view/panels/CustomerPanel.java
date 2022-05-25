package de.jprojekt.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.jprojekt.view.actions.ActionNewCustomer;

public class CustomerPanel extends JDialog implements ActionListener{
	protected JTextField fieldFirstName;
	protected JTextField fieldLastName;
	protected JTextField fieldAddress;
	protected JTextField fieldTelNumber;
	protected JTextField fieldAdvisor;
	protected JTextField fieldBirthday; //to be replaced by a datepicker
	
	protected JPanel content;
	
	
	public CustomerPanel(JFrame f, String titel) {
		super(f, titel, true);
		
		initialize();
	}
	
	protected void initialize() {
		
		content = new JPanel();
		content.setLayout(new GridLayout(0,2,5,5));
		
		
		fieldFirstName = new JTextField(10);
		//fieldFirstName.setBorder(BorderFactory.createLineBorder(Color.black));
		
		fieldLastName = new JTextField(10);
		//fieldLastName.setBorder(BorderFactory.createLineBorder(Color.black));
		
		fieldAddress = new JTextField(10);
		//fieldAddress.setBorder(BorderFactory.createLineBorder(Color.black));
		
		fieldTelNumber = new JTextField(10);
		//fieldTelNumber.setBorder(BorderFactory.createLineBorder(Color.black));
		
		fieldAdvisor = new JTextField(10);
		//fieldAdvisor.setBorder(BorderFactory.createLineBorder(Color.black));

		fieldBirthday = new JTextField(10);
		//fieldBirthday.setBorder(BorderFactory.createLineBorder(Color.black));

		
		JLabel lblFirstName = new JLabel("First Name");
		JLabel lblLastName = new JLabel("Last Name");
		JLabel lblAddress = new JLabel("Address");
		JLabel lblTelNummber = new JLabel("Phone Number");
		JLabel lblAdvisor = new JLabel("Advisor");
		JLabel lblBirthday = new JLabel("Birthday");
		
		
		content.add(lblFirstName);
		content.add(fieldFirstName);
		content.add(lblLastName);
		content.add(fieldLastName);
		content.add(lblBirthday);
		content.add(fieldBirthday);
		content.add(lblAddress);
		content.add(fieldAddress);
		content.add(lblTelNummber);
		content.add(fieldTelNumber);
		content.add(lblAdvisor);
		content.add(fieldAdvisor);
		
		add(content, BorderLayout.CENTER);
		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


