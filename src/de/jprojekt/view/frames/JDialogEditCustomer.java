package de.jprojekt.view.frames;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Checks;


public class JDialogEditCustomer extends JDialog {
	
	protected JTextField txtVorname;
	protected JTextField txtNachname;
	protected JTextField txtAdresse;
	protected JTextField txtPlz;
	
	protected JButton btnUpdateUser;
	
	
	private ICustomerController controller;

	
	public JDialogEditCustomer(JFrameAdapter owner, ICustomerController controller){
		super(owner);
		this.controller = controller;
		
		initializeComponent();
	}
	
	
	private void initializeComponent() {
		setLayout(new FlowLayout());
		
		
		
		JLabel lblVorname = new JLabel("*Vorname: ");
		add (lblVorname);
		txtVorname = new JTextField(15);
		add (txtVorname);
		JLabel lblNachname = new JLabel("*Nachname: ");
		add (lblNachname);
		txtNachname = new JTextField(15);
		add (txtNachname);
		//JLabel lblBDay = new JLabel("*Geburtstag: ");
		//add(lblBDay);
		
		
		
		JLabel adresse = new JLabel("*Adresse: ");
		add (adresse);
		txtAdresse = new JTextField(15);
		add (txtAdresse);
		JLabel plz = new JLabel("*PLZ: ");
		add (plz);
		txtPlz = new JTextField(15);
		add (txtPlz);
		
		btnUpdateUser = new JButton("Änderungen speichern");
		add(btnUpdateUser);
		
		//NewUserhandler handler = new NewUserhandler();
		btnUpdateUser.addActionListener(handler -> {
			
			try {
				validateData();
				
				Customer c = createCustomer();
				controller.update(c);
			}catch(BankingException e) {
				showDialog(e.getMessage());
			}catch(Exception e) {
				showDialog("Es ist ein Fehler aufgetreten.");
			}
			
		});
		
	}
	
	protected Customer createCustomer() {
		Customer c = new Customer();
		c.setFirstname(txtVorname.getText());
		c.setLastname(txtNachname.getText());
		c.setAddress(txtAdresse.getText());
		c.setPlz(Integer.getInteger(txtPlz.getText()));
		
		return c;
	}
	
	protected void validateData() throws BankingException {
		if(isAnyDataEmpty()) {
			throw new BankingException("Bitte Füllen Sie alle mit '*' markierten Datenfelder aus.");
		}
		
		checkVorname();
		checkNachname();
		checkPlz();
	}
	
	protected void checkVorname() throws BankingException{
		String vorname = txtVorname.getText();
		
		if(!Checks.isName(vorname))
			throw new BankingException("Vorname ist ungültig");
	}
	
	protected void checkNachname() throws BankingException{
		String nachname = txtNachname.getText();
		
		if(!Checks.isName(nachname))
			throw new BankingException("Nachname ist ungültig");
	}
	
	protected void checkPlz() throws BankingException{
		String plz = txtPlz.getText();
		
		if(!Checks.isPLZ(plz))
			throw new BankingException("PLZ ist ungültig");
	}
	
	
	protected boolean isAnyDataEmpty() {
		if (txtVorname.getText().equals("")) return true;
		if (txtNachname.getText().equals(""))return true;
		if (txtAdresse.getText().equals(""))return true;
		if(txtPlz.getText().equals(""))return true;
		
		return false;
	}
	
	
	protected void showDialog(String message) {
		JOptionPane.showMessageDialog(this.getOwner(), message);
	}
}


