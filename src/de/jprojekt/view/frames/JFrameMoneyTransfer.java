package de.jprojekt.view.frames;


import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;


public class JFrameMoneyTransfer extends JDialog{ 
		private JTextField txtName;
		private JTextField txtKontoID;
		private JTextField txtAmount;
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
			
			JLabel lblKontoNummer = new JLabel("Kontonummer");
			add (lblKontoNummer);
			txtKontoID = new JTextField(15);
			add (txtKontoID);
			
			JLabel lblAmount = new JLabel("Betrag");
			add (lblAmount);
			txtAmount = new JTextField(15);
			add(txtAmount);
			
			btnTransfer = new JButton("Überweisen");
			add(btnTransfer);
			
			btnTransfer.addActionListener(handler ->{
				
				try{
					validateData();
					
					BankAccount srcKonto =(BankAccount) cboSrcKonto.getSelectedItem();
					BankAccount targetKonto = controller.getAccountByID(txtKontoID.getText());
					
					long amount = (long) Integer.parseInt(txtAmount.getText()); 
					
					controller.transferMoney(srcKonto, targetKonto, amount);
					
					JOptionPane.showMessageDialog(this.getOwner(), "Überweisung erfolgreich abgeschlossen!");
				}
				catch(BankingException e) {
					JOptionPane.showMessageDialog(this.getOwner(), e.getMessage());
					System.out.println(e.getMessage());
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.getOwner(), "Überweisung fehlgeschlagen!");
					System.out.println(e.getMessage());
				}
			});
		}	
		
		private void validateData() throws BankingException {
			if(txtName.getText().isEmpty() || txtKontoID.getText().isEmpty() || txtAmount.getText().isEmpty()){
				throw new BankingException("Bitte füllen Sie alle Felder aus!");
			}
		}
		
		
	}
				
					

