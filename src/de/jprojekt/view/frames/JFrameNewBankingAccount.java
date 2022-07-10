package de.jprojekt.view.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFrameNewBankingAccount extends JFrame {

	private String[] messageStrings = {"Girokonto", "Sparkonto"};
	private JComboBox cmbMessageList = new JComboBox(messageStrings);
	private JLabel lblText = new JLabel();
	private JTextArea lblArea =  new JTextArea();
	private JButton btnabschicken;
	

	
	public JFrameNewBankingAccount() {
		super("Konto Auswahl");
		setLayout(new FlowLayout());
	
	newAccHandler handler = new newAccHandler();
	cmbMessageList.setSelectedIndex(0);
	cmbMessageList.addActionListener(new newAccHandler());
	add(cmbMessageList);
	add(lblArea);
	lblArea.setWrapStyleWord(true);
	btnabschicken = new JButton("Jetzt Konto anfordern");
	add(btnabschicken);
	}
	
	private class newAccHandler implements ActionListener{
		
	@Override
	public void actionPerformed(ActionEvent e) {	
		
	
		if(e.getSource() == cmbMessageList) {
			JComboBox cb = (JComboBox)e.getSource();
			String msg = (String)cb.getSelectedItem();
			switch (msg) {
			case "Girokonto": lblArea.setText("Kostenlose Bargeldauszahlung an über 23.000 Geldautomaten in ganz Deutschland\r\n"
					+ "Kontoservice in jeder Lebenslage: persönlich in der Filiale, per Video-Chat, an unseren SB-Terminals, telefonisch, online oder mobil mit der ausgezeichneten Sparkassen-App\r\n"
					+ "Bargeldlos und kontaktlos bezahlen mit der Sparkassen-Card (Debitkarte) und optional der Sparkassen-Kreditkarte \r\n"
					+ "Kontaktlos bezahlen mit Karte oder Smartphone\r\n"
					+ "");
					break;
			case "Sparkonto": lblArea.setText("Unkomplizierte Sparmöglichkeit\r\n"
					+ "Hohe Sicherheit\r\n"
					+ "Flexibel einzahlen oder in festen Raten sparen       \r\n"
					+ "Keine Gebühren\r\n"
					+ "Bis zu 2.000 Euro monatlich verfügbar");
				break;
			default: lblText.setText("Ein Fehler ist aufgetreten");}}

		}
	}
}

