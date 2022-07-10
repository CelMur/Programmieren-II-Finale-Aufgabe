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
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Checks;


public class JFrameNewCustomer extends JDialog {
	
	protected JTextField txtVorname;
	protected JTextField txtNachname;
	protected JTextField txtAdresse;
	protected JTextField txtPlz;
	
	
	
	protected JButton btnCreateUser;
	protected JPasswordField txtPassword;
	protected JPasswordField txtRepeatPassword;
	
	
	private ICustomerController controller;

	
	public JFrameNewCustomer(JFrameAdapter owner, ICustomerController controller){
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
		JLabel lblBDay = new JLabel("*Geburtstag: ");
		add(lblBDay);
		
		
		
		JLabel adresse = new JLabel("*Adresse: ");
		add (adresse);
		txtAdresse = new JTextField(15);
		add (txtAdresse);
		JLabel plz = new JLabel("*PLZ: ");
		add (plz);
		txtPlz = new JTextField(15);
		add (txtPlz);
		
		
		JLabel label2 = new JLabel("*Passwort: ");
		add (label2);
		txtPassword = new JPasswordField(15);
		add (txtPassword);
		JLabel repasswort = new JLabel("*Passwort wiederholen: ");
		add (repasswort);
		txtRepeatPassword = new JPasswordField(15);
		add (txtRepeatPassword);

		btnCreateUser = new JButton("Nutzer erstellen");
		add(btnCreateUser);
		
		//NewUserhandler handler = new NewUserhandler();
		btnCreateUser.addActionListener(handler -> {
			
			try {
				validatePassword();
				validateData();
				
				Customer c = createCustomer();
				controller.create(c);
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
	
	
	protected void validatePassword() throws BankingException{
		String password = new String(txtPassword.getPassword());
		String repeatPassword = new String(txtRepeatPassword.getPassword());
		
		checkPasswordEquals(password, repeatPassword);
		checkPasswordIsSet(password);
		checkReapeatPasswordIsSet(repeatPassword);
		checkPassword(password);
	}
	
	protected void checkPasswordEquals(String password, String repeatPassword) throws BankingException {
		if (!password.equals(repeatPassword)) 
			throw new BankingException("Passwort und Passwort-Wdh. stimmen nicht überein.");
	}
	
	protected void checkPasswordIsSet(String password) throws BankingException {
		if (password.equals("")) 
			throw new BankingException("Passwort darf nicht leer sein");
	}
	
	protected void checkReapeatPasswordIsSet(String repeatPassword) throws BankingException {
		if (repeatPassword.equals("")) 
			throw new BankingException("Passwort-Wdh. darf nicht leer sein");
	}	
	
	protected void checkPassword(String password) throws BankingException {
		if(!Checks.isPassword(password)) 
			throw new BankingException("Passwort ist ungültig");
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


