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


public class JDialogEditEmployee extends JDialog {
	
	protected JTextField txtVorname;
	protected JTextField txtNachname;
	
	protected JButton btnUpdateUser;
	
	private IEmployeeController controller;
	private Employee employee;

	
	public JDialogEditEmployee(JFrameAdapter owner, IEmployeeController controller, Employee employee){
		super(owner);
		this.controller = controller;
		this.employee = employee;
		
		initializeComponent();
		initializeValues();
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
		

		btnUpdateUser = new JButton("Änderungen Speichern");
		add(btnUpdateUser);
		
		btnUpdateUser.addActionListener(handler -> {
			
			try { 
				validateData();
				updateData();
				
				controller.update(employee);				
			}catch(BankingException e) {
				showDialog(e.getMessage());
			}catch(Exception e) {
				showDialog("Es ist ein Fehler aufgetreten.");
			}
			
		});
		
	}
	
	private void initializeValues() {
		txtVorname.setText(employee.getFirstname());
		txtNachname.setText(employee.getLastname());
	}
	
	protected void updateData() {
		
		employee.setFirstname(txtVorname.getText());
		employee.setLastname(txtNachname.getText());
		
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


