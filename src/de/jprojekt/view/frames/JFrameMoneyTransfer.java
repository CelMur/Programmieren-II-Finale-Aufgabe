package de.jprojekt.view.frames;

import java.awt.Adjustable;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFrameMoneyTransfer extends JFrame{ 
		private JTextField Name;
		private JTextField IBAN;
		private JTextArea Zweck;
		private JButton btnAbsenden;
		private JLabel BeschreibungName;
		private JLabel BeschreibungIban;
		private JLabel BeschreibungZweck;
		private JRadioButton Transfer;

		
		public JFrameMoneyTransfer() {
			super("Geldtransfer");
			setLayout(new FlowLayout());
			

	        JRadioButton weisungButton = new JRadioButton("Überweisung");
	        this.add(weisungButton);
	        weisungButton.setSelected(true);

	        JRadioButton tragButton = new JRadioButton("Übertrag");
	        this.add(tragButton);
			
			ButtonGroup group = new ButtonGroup();
	        group.add(weisungButton);
	        group.add(tragButton);
			
			BeschreibungName = new JLabel("Name");
			add (BeschreibungName);
			Name = new JTextField(15);
			add (Name);
			
			BeschreibungIban = new JLabel("Kontonummer");
			add (BeschreibungIban);
			IBAN = new JTextField(15);
			add (IBAN);
			
			BeschreibungZweck = new JLabel("Verwendungszweck");
			add (BeschreibungZweck);
			Zweck = new JTextArea(5,15);
			add (Zweck);
			
			btnAbsenden = new JButton("Freigeben");
			add(btnAbsenden);
			
			UerberweisungtragHandler handler = new UerberweisungtragHandler();
			weisungButton.addActionListener(handler);
			tragButton.addActionListener(handler);
			btnAbsenden.addActionListener(handler);}			
		
		private class UerberweisungtragHandler implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent event) {
			//	if (event.getSource()==tragButton) {
					//Schau auf Ktos des Nutzers
					//if{Kto.equals(KTO vom User){
						//JOptionPane.showMessageDialog(null, "Passwort geändert!")
						// GELD VERSCHIEBEN
					//else if {
					}
				}
			
			}
					

