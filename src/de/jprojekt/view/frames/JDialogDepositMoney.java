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
import javax.swing.JTextField;
	
public class JDialogDepositMoney extends JFrame{ 
		private JTextField money;
		private JButton einzahlen;
		private JButton auszahlen;
		private JLabel label1;
		private JLabel label2;
		private JPanel up;
		private JPanel lp;

		public JDialogDepositMoney(){
			super("Geldautomat");
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
					JOptionPane.showMessageDialog(null, "Sie haben den folgenden Betrag abgehoben: " +  intMoney );
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