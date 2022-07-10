package de.jprojekt.view.frames;
	  
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;
	
public class JDialogDepositMoney extends JDialog{ 
		private JTextField money;
		private JButton einzahlen;
		private JButton auszahlen;
		private JLabel label1;
		private JLabel label2;
		private JPanel up;
		private JPanel lp;

		private IBankAccountController controller;
		
		public JDialogDepositMoney(JFrameAdapter frame, IBankAccountController controller){
			super(frame);
			this.controller = controller;
			
			setLayout(new FlowLayout());
			
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
			einzahlen = new JButton("Einzahlen");
			add(einzahlen);
			auszahlen = new JButton("Auszahlen");
			add(auszahlen);
			
			
			
			einzahlen.addActionListener(handler ->{
				ApplicationData appData = ApplicationData.getInstance();
				
				Customer currentUser = (Customer) appData.getCurrentUser();
				
				
				long betrag=Integer.parseInt(money.getText());
				
				try {
					//TODO:
					controller.depositMoney(null, null, betrag);
				} catch (BankingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(Exception e) {
					
				}
				JOptionPane.showMessageDialog(getOwner(), "Sie haben den folgenden Betrag eingezahlt: " +  " " + betrag );
				// Übergabe an Konto
				// KtoVariable = KtoVariable + intMoney
				
			});
		}
}