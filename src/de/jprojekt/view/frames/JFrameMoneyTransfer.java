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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.jprojekt.controller.BankAccountController;
import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.mysql.DBAccount;
import de.jprojekt.utils.mysql.DBCustomer;

public class JFrameMoneyTransfer extends JDialog{ 
		private JTextField txtName;
		private JTextField txtKontoID;
		private JTextArea txtAmount;
		private JButton btnTransfer;
		
		private JComboBox cboSrcKonto;
		
		
		private IBankAccountController controller;

		
		public JFrameMoneyTransfer(JFrameAdapter frame, IBankAccountController controller) {
			super(frame);
			
			this.controller = controller;
			setTitle("Geldüberweisung");
			
			setLayout(new FlowLayout());
			setSize(400, 400);
			String userid = ApplicationData.getInstance().getCurrentUser().getId();
			BankAccount[] accounts;
			
			try{
				Customer currUser = (Customer) ApplicationData.getInstance().getCurrentUser();
				accounts = new BankAccount[currUser.getBankAccounts().size()];
				accounts = currUser.getBankAccounts().toArray(accounts);
				cboSrcKonto = new JComboBox(new DefaultComboBoxModel(accounts));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Keine Konten vorhanden");
				System.out.println(e + " Dantenbankfehler");
			}

			cboSrcKonto.setEditable(false);
			JLabel lblBeschreibungsrc = new JLabel("Ursprungskonto");
			add(lblBeschreibungsrc);
			add(cboSrcKonto);
	
			JLabel lblBeschreibungName = new JLabel("txtName");
			add (lblBeschreibungName);
			txtName = new JTextField(15);
			add (txtName);
			
			JLabel lblBeschreibungIban = new JLabel("Kontonummer");
			add (lblBeschreibungIban);
			txtKontoID = new JTextField(15);
			add (txtKontoID);
			
			JLabel lblBeschreibungZweck = new JLabel("Verwendungszweck");
			add (lblBeschreibungZweck);
			txtAmount = new JTextArea(5,15);
			add(txtAmount);
			
			btnTransfer = new JButton("Freigeben");
			add(btnTransfer);
			
			btnTransfer.addActionListener(handler ->{
				if(txtName.getText().isEmpty() || txtKontoID.getText().isEmpty() || txtAmount.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus!");
				}
				else{
					try{
						BankAccount srcKonto =(BankAccount) cboSrcKonto.getSelectedItem();
						BankAccount targetKonto = controller.getAccountByID(txtKontoID.getText());
						long amount = (long) Integer.parseInt(txtAmount.getText()); 
						
						controller.transferMoney(srcKonto, targetKonto, amount);
						JOptionPane.showMessageDialog(null, "Überweisung erfolgreich abgeschlossen!");
					}
					catch(BankingException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						System.out.println(e.getMessage());
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Überweisung fehlgeschlagen!");
						System.out.println(e.getMessage());
					}
				}
			});
		}			
		
		
	}
				
					

