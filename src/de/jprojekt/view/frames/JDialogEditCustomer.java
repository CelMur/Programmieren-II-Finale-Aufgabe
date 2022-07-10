package de.jprojekt.view.frames;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Checks;


public class JDialogEditCustomer extends JDialog {
	
	protected JTextField txtVorname;
	protected JTextField txtNachname;
	protected JTextField txtAdresse;
	protected JTextField txtPlz;
	
	protected JButton btnUpdateUser;
	
	
	private ICustomerController controller;
	
	private Customer customer;

	
	public JDialogEditCustomer(JFrameAdapter owner, ICustomerController controller, Customer customer){
		super(owner);
		this.controller = controller;
		this.customer = customer;
		
		initializeComponent();
		initializeValues();
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
		
		btnUpdateUser = new JButton("�nderungen speichern");
		add(btnUpdateUser);
		
		//NewUserhandler handler = new NewUserhandler();
		btnUpdateUser.addActionListener(handler -> {
			
			try {
				validateData();
				updateData();
				
				controller.update(customer);
			}catch(BankingException e) {
				showDialog(e.getMessage());
			}catch(Exception e) {
				showDialog("Es ist ein Fehler aufgetreten.");
			}
			
		});
		
	}
	
	private void initializeValues() {
		txtVorname.setText(customer.getFirstname());
		txtNachname.setText(customer.getLastname());
	}
	
	private void updateData() {
		
		customer.setFirstname(txtVorname.getText());
		customer.setLastname(txtNachname.getText());
		customer.setAddress(txtAdresse.getText());
		customer.setPlz(Integer.parseInt(txtPlz.getText()));
	}
	
	private void validateData() throws BankingException {
		if(isAnyDataEmpty()) {
			throw new BankingException("Bitte F�llen Sie alle mit '*' markierten Datenfelder aus.");
		}
		
		checkVorname();
		checkNachname();
		checkPlz();
	}
	
	private void checkVorname() throws BankingException{
		String vorname = txtVorname.getText();
		
		if(!Checks.isName(vorname))
			throw new BankingException("Vorname ist ung�ltig");
	}
	
	private void checkNachname() throws BankingException{
		String nachname = txtNachname.getText();
		
		if(!Checks.isName(nachname))
			throw new BankingException("Nachname ist ung�ltig");
	}
	
	private void checkPlz() throws BankingException{
		String plz = txtPlz.getText();
		
		if(!Checks.isPLZ(plz))
			throw new BankingException("PLZ ist ung�ltig");
	}
	
	
	private boolean isAnyDataEmpty() {
		if (txtVorname.getText().equals("")) return true;
		if (txtNachname.getText().equals(""))return true;
		if (txtAdresse.getText().equals(""))return true;
		if(txtPlz.getText().equals(""))return true;
		
		return false;
	}
	
	
	private void showDialog(String message) {
		JOptionPane.showMessageDialog(this.getOwner(), message);
	}
}


