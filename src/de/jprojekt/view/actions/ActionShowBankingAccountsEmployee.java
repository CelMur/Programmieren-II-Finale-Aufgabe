package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.main.ApplicationController;
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
		//IEmployeeController controller = appController.getEmployeeController(); 
		JFrameAdapter frame = getFrame();
		
		JDialog dialog = new JDialog(frame);
		JPanel p = new JPanelKontenUebersicht(null);
		
		
		
		dialog.setTitle("Zugewiesene Konten");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		
	}
}
