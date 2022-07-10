package de.jprojekt.view.frames;
	  
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
	
public class JDialogWithdrawMoney extends JDialog{ 
		private JTextField money;
		private JButton einzahlen;
		private JButton auszahlen;
		private JLabel label1;
		private JLabel label2;
		private JPanel up;
		private JPanel lp;
		private JLabel Beschreibungsrc;
		JComboBox srcKonto;
		
		private IBankAccountController controller;

		public JDialogWithdrawMoney(JFrameAdapter frame, IBankAccountController controller){
			super(frame);
			setLayout(new FlowLayout());
			this.controller = controller;
			
			setTitle("Geldabheben");
			String userid = ApplicationData.getInstance().getCurrentUser().getId();
			BankAccount[] accounts;
			try{
				Customer currUser = (Customer) ApplicationData.getInstance().getCurrentUser();
				accounts = new BankAccount[currUser.getBankAccounts().size()];
				accounts = currUser.getBankAccounts().toArray(accounts);
				srcKonto = new JComboBox(new DefaultComboBoxModel(accounts));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Keine Konten vorhanden");
				System.out.println(e + "Dantenbankfehler");
			}

			srcKonto.setEditable(false);
			Beschreibungsrc = new JLabel("Ursprungskonto");
			add(Beschreibungsrc);
			add(srcKonto);
			
			up = new JPanel();	
			up.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			label1 = new JLabel("Geldbetrag:");
			add (label1);		
			money =  new JTextField(15);
			add (money);
			
			
			lp = new JPanel();
			lp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			label2 = new JLabel("Auswahl");
			add (label2);
			auszahlen = new JButton("Auszahlen");
			add(auszahlen);

			
			auszahlen.addActionListener(handler -> {
				long betrag=Integer.parseInt(money.getText());
				Customer currUser = (Customer) ApplicationData.getInstance().getCurrentUser();
				BankAccount Konto =(BankAccount) srcKonto.getSelectedItem();
				
				try{
					controller.withdrawMoney(currUser, Konto, betrag);
					JOptionPane.showMessageDialog(getOwner(), "Sie haben den folgenden Betrag abgehoben: " +  betrag );
			    }catch(Exception e){
			    	JOptionPane.showMessageDialog(getOwner(), "Sie haben nicht genug Geld auf Ihrem Konto");
			    } 
			});
		}
		
		
}