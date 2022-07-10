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
import javax.swing.JPasswordField;

import de.jprojekt.controller.interfaces.IUserController;
import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Checks;
import de.jprojekt.utils.mysql.DBUser;

public class JDialogChangePassword extends JFrame {
	private JPasswordField oldPw;
	private JPasswordField newPw;
	private JPasswordField reNewPw;
	private JButton btnChange;
	private JPanel up;
	private JPanel lp;
	private User user;
	private IUserController userController;

	public JDialogChangePassword(User user, IUserController userController) {
		super("Passwort ändern");

		this.user = user;
		this.userController = userController;

		setLayout(new FlowLayout());

		up = new JPanel();
		up.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel label1 = new JLabel("Altes Passwort");
		add(label1);

		oldPw = new JPasswordField(15);
		add(oldPw);

		lp = new JPanel();
		lp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel label2 = new JLabel("Neues Passwort");
		add(label2);

		newPw = new JPasswordField(15);
		add(newPw);

		JLabel label3 = new JLabel("Neues Passwort wiederholen");
		add(label3);

		reNewPw = new JPasswordField(15);
		add(reNewPw);

		btnChange = new JButton("Passwort ändern");
		add(btnChange);

		btnChange.addActionListener(handler -> {
			try {
				validatePassword();
				DBUser.setPassword(this.user.getId(), new String(newPw.getPassword()));
			} catch(BankingException e) {
				JOptionPane.showMessageDialog(this.getOwner(), e.getMessage());
			} catch(Exception e) {
				JOptionPane.showMessageDialog(this.getOwner(), "Ein Fehler ist aufgetreten");
			}
		});
	}

	protected void validatePassword() throws BankingException {
		String passwordOld = new String(oldPw.getPassword());
		String passwordNew = new String(newPw.getPassword());
		String passwordNewRepeat = new String(reNewPw.getPassword());

		checkOldPassword(passwordOld);
		checkPasswordEquals(passwordNew, passwordNewRepeat);
		checkPasswordIsSet(passwordNew);
		checkReapeatPasswordIsSet(passwordNewRepeat);
		checkPassword(passwordNew);
	}

	protected void checkOldPassword(String password) throws BankingException {
		if (!this.userController.isPasswordValid(this.user, password)) {
			throw new BankingException("Das alte Passwort ist falsch.");
		}
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
		if (!Checks.isPassword(password))
			throw new BankingException("Neues Passwort ist ungültig");
	}
}
