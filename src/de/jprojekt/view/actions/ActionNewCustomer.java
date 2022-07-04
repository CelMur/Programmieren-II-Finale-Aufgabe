package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;

import de.jprojekt.view.panels.CustomerDataPanel;

public class ActionNewCustomer extends AbstractActionAdapter{
	
	private JFrame frame;
	
	public ActionNewCustomer(JFrame frame) {
		this.frame = frame;
		
		putValue(NAME, "Kunde anlegen");
		putValue(SHORT_DESCRIPTION, "öffnet den Dialog zum anlegen eines neuen Kunden");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JDialog dialog = new CustomerDataPanel(this.frame, "Neuer Kunde");
		
		dialog.setVisible(true);
		
	}

}
