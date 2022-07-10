package de.jprojekt.view.frames;

import java.awt.Adjustable;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.jprojekt.controller.BankAccountController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.mysql.DBAccount;
import de.jprojekt.utils.mysql.DBCustomer;

public class JFrameMoneyTransfer extends JFrame{ 
		private JTextField Name;
		private JTextField IBAN;
		private JTextArea Zweck;
		private JButton btnAbsenden;
		private JLabel BeschreibungName;
		private JLabel BeschreibungIban;
		private JLabel BeschreibungZweck;
		private JLabel Beschreibungsrc;
		JComboBox srcKonto;
		private JRadioButton Transfer;

		
		public JFrameMoneyTransfer() {
			super("Geldtransfer");
			setLayout(new FlowLayout());
			setSize(400, 400);
			String userid = ApplicationData.getInstance().getCurrentUser().getId();
			BankAccount[] accounts;
			try{
				accounts = new BankAccount[DBCustomer.getCustomer(userid).getBankAccounts().size()];
				accounts = DBCustomer.getCustomer(userid).getBankAccounts().toArray(accounts);
				srcKonto = new JComboBox(new DefaultComboBoxModel(accounts));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Keine Konten vorhanden");
				System.out.println(e + "Dantenbankfehler");
			}


			
			
			srcKonto.setEditable(false);
			Beschreibungsrc = new JLabel("Ursprungskonto");
			add(Beschreibungsrc);
			add(srcKonto);


	        JRadioButton weisungButton = new JRadioButton("Überweisung");
	        this.add(weisungButton);
	        weisungButton.setSelected(true);


			

			ButtonGroup group = new ButtonGroup();
	        group.add(weisungButton);
			
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
			btnAbsenden.addActionListener(handler);}			
		
		private class UerberweisungtragHandler implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == btnAbsenden){
					if(Name.getText().isEmpty() || IBAN.getText().isEmpty() || Zweck.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus!");
					}
					else{
						try{
						BankAccount Konto =(BankAccount) srcKonto.getSelectedItem();
						ApplicationController.getInstance().getBankAccountController().transferMoney(Konto, DBAccount.getAccount(IBAN.getText()), (long) Integer.parseInt(Zweck.getText()));
						JOptionPane.showMessageDialog(null, "Überweisung erfolgreich abgeschlossen!");
						}
						catch(Exception e){
							JOptionPane.showMessageDialog(null, "Überweisung fehlgeschlagen!");
							System.out.println(e.getMessage());
						}
					}
			}
			//	if (event.getSource()==tragButton) {
					//Schau auf Ktos des Nutzers
					//if{Kto.equals(KTO vom User){
						//JOptionPane.showMessageDialog(null, "Passwort geändert!")
						// GELD VERSCHIEBEN
					//else if {
		}
	}
		
}			
					

