package de.jprojekt.view.actions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.view.frames.JDialogNewEmpolyee;
import de.jprojekt.view.frames.JFrameAdapter;
import de.jprojekt.view.frames.JPanelKontenUebersicht;

public class ActionShowBankingAccountsEmployee extends AbstractActionAdapter{
	public ActionShowBankingAccountsEmployee(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Zugewiesene Konten Anzeigen");
		putValue(SHORT_DESCRIPTION, "zeigt die dem Banker zugewiesenen Konten an");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		ApplicationData appData = ApplicationData.getInstance();
		
		Employee employee = (Employee) appData.getCurrentUser();
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JDialog(frame);
		
		List<BankAccount> list = new ArrayList<BankAccount>();
		
		for (Customer c : employee.getCustomers()) {
			list.addAll(c.getBankAccounts());
		}
		
		//TODO:
		JPanel p = new JPanelKontenUebersicht(list);
		
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(p, BorderLayout.CENTER);
		
		dialog.setTitle("Zugewiesene Konten");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
	}
}
