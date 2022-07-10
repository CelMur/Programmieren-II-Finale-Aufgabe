package de.jprojekt.view.frames;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Checks;

public class JDialogNewCustomer extends JDialog {

	protected JTextField txtVorname;
	protected JTextField txtNachname;
	protected JTextField txtAdresse;
	protected JTextField txtPlz;

	protected JButton btnCreateUser;
	protected JPasswordField txtPassword;
	protected JPasswordField txtRepeatPassword;

	private ICustomerController controller;
	private ApplicationData appData;

	public JDialogNewCustomer(JFrameAdapter owner, ICustomerController controller) {
		super(owner);
		this.controller = controller;
		initializeComponent();
		appData = ApplicationData.getInstance();
	}

	private void initializeComponent() {
		setLayout(new FlowLayout());

		JLabel lblVorname = new JLabel("*Vorname: ");
		add(lblVorname);
		txtVorname = new JTextField(15);
		add(txtVorname);
		JLabel lblNachname = new JLabel("*Nachname: ");
		add(lblNachname);
		txtNachname = new JTextField(15);
		add(txtNachname);
		// JLabel lblBDay = new JLabel("*Geburtstag: ");
		// add(lblBDay);

		JLabel adresse = new JLabel("*Adresse: ");
		add(adresse);
		txtAdresse = new JTextField(15);
		add(txtAdresse);
		JLabel plz = new JLabel("*PLZ: ");
		add(plz);
		txtPlz = new JTextField(15);
		add(txtPlz);

		JLabel label2 = new JLabel("*Passwort: ");
		add(label2);
		txtPassword = new JPasswordField(15);
		add(txtPassword);
		JLabel repasswort = new JLabel("*Passwort wiederholen: ");
		add(repasswort);
		txtRepeatPassword = new JPasswordField(15);
		add(txtRepeatPassword);

		btnCreateUser = new JButton("Nutzer erstellen");
		add(btnCreateUser);

		btnCreateUser.addActionListener(handler -> {

			try {
				validatePassword();
				validateData();

				Customer c = createCustomer();
				controller.create(c);
				StringSelection selection = new StringSelection(c.getId());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);

				showDialog("Bitte notieren Sie sich folgende Login-ID (ins Clipboard kopiert): " + c.getId());
				setVisible(false);
			} catch (BankingException e) {
				showDialog(e.getMessage());
			} catch (Exception e) {
				showDialog("Es ist ein Fehler aufgetreten.");
			}

		});

	}

	protected Customer createCustomer() {
		Customer c = new Customer();
		c.setAdviser((Employee) appData.getCurrentUser());
		c.setFirstname(txtVorname.getText());
		c.setLastname(txtNachname.getText());
		c.setAddress(txtAdresse.getText());
		c.setPlz(Integer.parseInt(txtPlz.getText()));
		c.setPassword(new String(txtPassword.getPassword()));
		c.setBday("2022-07-10");
		return c;
	}

	protected void validatePassword() throws BankingException {
		String password = new String(txtPassword.getPassword());
		String repeatPassword = new String(txtRepeatPassword.getPassword());

		checkPasswordEquals(password, repeatPassword);
		checkPasswordIsSet(password);
		checkReapeatPasswordIsSet(repeatPassword);
		checkPassword(password);
	}

	protected void checkPasswordEquals(String password, String repeatPassword) throws BankingException {
		if (!password.equals(repeatPassword))
			throw new BankingException("Passwort und Passwort-Wdh. stimmen nicht �berein.");
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
		if (!Checks.isPassword(password))
			throw new BankingException("Passwort ist ung�ltig");
	}

	protected void validateData() throws BankingException {
		if (isAnyDataEmpty()) {
			throw new BankingException("Bitte F�llen Sie alle mit '*' markierten Datenfelder aus.");
		}

		checkVorname();
		checkNachname();
		checkPlz();
	}

	protected void checkVorname() throws BankingException {
		String vorname = txtVorname.getText();

		if (!Checks.isName(vorname))
			throw new BankingException("Vorname ist ung�ltig");
	}

	protected void checkNachname() throws BankingException {
		String nachname = txtNachname.getText();

		if (!Checks.isName(nachname))
			throw new BankingException("Nachname ist ung�ltig");
	}

	protected void checkPlz() throws BankingException {
		String plz = txtPlz.getText();

		if (!Checks.isPLZ(plz))
			throw new BankingException("PLZ ist ung�ltig");
	}

	protected boolean isAnyDataEmpty() {
		if (txtVorname.getText().equals(""))
			return true;
		if (txtNachname.getText().equals(""))
			return true;
		if (txtAdresse.getText().equals(""))
			return true;
		if (txtPlz.getText().equals(""))
			return true;

		return false;
	}

	protected void showDialog(String message) {
		JOptionPane.showMessageDialog(this.getOwner(), message);
	}
}
