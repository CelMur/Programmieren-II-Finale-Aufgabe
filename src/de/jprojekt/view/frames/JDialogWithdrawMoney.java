package de.jprojekt.view.frames;
	  
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
	
public class JDialogWithdrawMoney extends JFrame{ 
		private JTextField money;
		private JButton einzahlen;
		private JButton auszahlen;
		private JLabel label1;
		private JLabel label2;
		private JPanel up;
		private JPanel lp;
		private JLabel Beschreibungsrc;
		JComboBox srcKonto;

		public JDialogWithdrawMoney(){
			super("Geldautomat");
			setLayout(new FlowLayout());

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

			
			
			
			
			GeldautomatHandler handler = new GeldautomatHandler();
			money.addActionListener(handler);
			einzahlen.addActionListener(handler);
			auszahlen.addActionListener(handler);
		}
		
		private class GeldautomatHandler implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent event) {
				int intMoney = 0;
				

				if (event.getSource()==auszahlen) {
				
					intMoney=Integer.parseInt(money.getText());
					Customer currUser = (Customer) ApplicationData.getInstance().getCurrentUser();
					BankAccount Konto =(BankAccount) srcKonto.getSelectedItem();
					try{
					ApplicationController.getInstance().getBankAccountController().withdrawMoney(currUser, Konto, intMoney);
					JOptionPane.showMessageDialog(null, "Sie haben den folgenden Betrag abgehoben: " +  intMoney );
				    }catch(Exception e){
				    	JOptionPane.showMessageDialog(null, "Sie haben nicht genug Geld auf Ihrem Konto");
				    } 
					// Übergabe an Konto
					// KtoVariable = KtoVariable - intMoney
					}
					
				else if (event.getSource()==einzahlen) {
					intMoney=Integer.parseInt(money.getText());
					JOptionPane.showMessageDialog(null, "Sie haben den folgenden Betrag eingezahlt: " +  " " + intMoney );
					// Übergabe an Konto
					// KtoVariable = KtoVariable + intMoney
					}
				}
		}
}