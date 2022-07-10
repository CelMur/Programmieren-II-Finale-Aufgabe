package de.jprojekt.view.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Checks;


public class JFrameNewEmpolyee extends JDialog {
	
	protected JTextField txtVorname;
	protected JTextField txtNachname;
	
	protected JButton btnCreateUser;
	protected JPasswordField txtPassword;
	protected JPasswordField txtRepeatPassword;
	
	
	private IEmployeeController controller;

	
	public JFrameNewEmpolyee(JFrameAdapter owner, IEmployeeController controller){
		super(owner);
		this.controller = controller;
		initializeComponent();
	}
	
	
	private void initializeComponent() {
		setLayout(new FlowLayout());
		
		JLabel vorname = new JLabel("*Vorname: ");
		add (vorname);
		txtVorname = new JTextField(15);
		add (txtVorname);
		JLabel nachname = new JLabel("*Nachname: ");
		add (nachname);
		txtNachname = new JTextField(15);
		add (txtNachname);
		
		
		JLabel label2 = new JLabel("Passwort: ");
		add (label2);
		txtPassword = new JPasswordField(15);
		add (txtPassword);
		JLabel repasswort = new JLabel("Passwort wiederholen: ");
		add (repasswort);
		txtRepeatPassword = new JPasswordField(15);
		add (txtRepeatPassword);

		btnCreateUser = new JButton("Banker erstellen");
		add(btnCreateUser);
		
		btnCreateUser.addActionListener(handler -> {
			
			try {
				validatePassword();
				validateData();
				
				Employee employee = createEmployee();
				controller.create(employee);
			}catch(BankingException e) {
				showDialog(e.getMessage());
			}catch(Exception e) {
				showDialog("Es ist ein Fehler aufgetreten.");
			}
			
		});
		
	}
	
	protected Employee createEmployee() {
		Employee e = new Employee();
		e.setFirstname(txtVorname.getText());
		e.setLastname(txtNachname.getText());
		e.setPassword(new String(txtPassword.getPassword()));
		e.setBday("2022-07-10");
		
		return e;
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
	
	protected boolean isAnyDataEmpty() {
		if (txtVorname.getText().equals("")) return true;
		if (txtNachname.getText().equals(""))return true;
	
		return false;
	}
	
	
	protected void showDialog(String message) {
		JOptionPane.showMessageDialog(this.getOwner(), message);
	}
}


