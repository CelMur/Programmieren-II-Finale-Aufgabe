package de.jprojekt.view.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.BankAccount.BankAccountType;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;

public class JDialogNewBankAccount extends JDialog {

	
	private JComboBox cboBankAccountType;
	
	private JTextField txtName;
	private JButton btnAnlegen;
	
	private IBankAccountController controller;
	
	private ApplicationData appData;
	
	public JDialogNewBankAccount(JFrame owner, IBankAccountController controller) {
		super(owner);
		this.controller = controller;
		
		appData = ApplicationData.getInstance();
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		setTitle("Neues Konto");
		setLayout(new FlowLayout());
		
		JLabel lblName = new JLabel("Konto-Bezeichnung");
		add(lblName);
		
		txtName = new JTextField(15);
		add(txtName);
		
		JLabel lblBankAccountTypes = new JLabel("Konto-Typ");
		add(lblBankAccountTypes);
		
		String[] bankAccountTypeNames = {"Keine Auswahl", "Girokonto", "Sparkonto", "Depot"};
		cboBankAccountType = new JComboBox(bankAccountTypeNames);
		
		
		//cboBankAccountType.setSelectedIndex(0);
		add(cboBankAccountType);
		
		btnAnlegen = new JButton("Konto anlegen");
		add(btnAnlegen);
		
		btnAnlegen.addActionListener(handler -> {
			try {
				Customer currentUser = (Customer) appData.getCurrentUser();
			
				switch (cboBankAccountType.getSelectedItem().toString()) {
					case "Girokonto": 
						controller.create(currentUser, getName(), BankAccountType.GIRO.value());
						this.setVisible(false);
						break;
					case "Sparkonto": 
						controller.create(currentUser, getName(), BankAccountType.SAVING.value());
						this.setVisible(false);
						break;
					case "Depot":
						controller.create(currentUser, getName(), BankAccountType.DEPOSIT.value());
						this.setVisible(false);
						break;
					default: 
						JOptionPane.showMessageDialog(this.getOwner(), "Bitte Konto-Typ auswählen");
				}
			}catch(BankingException e) {
				JOptionPane.showMessageDialog(this.getOwner(), e.getMessage());
				this.setVisible(false);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this.getOwner(), "Konto konnte nicht angelegt werden");
				this.setVisible(false);
			}
			
		});
	}
	
}

