package de.jprojekt.view.actions;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;

import de.jprojekt.controller.UserController;
import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.controller.interfaces.IUserController;
import de.jprojekt.data.models.User;
import de.jprojekt.main.ApplicationController;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.view.frames.JDialogChangePassword;
import de.jprojekt.view.frames.JFrameAdapter;

public class ActionChangePassword extends AbstractActionAdapter{
	
	public ActionChangePassword(JFrameAdapter frame) {
		super(frame);
		
		putValue(NAME, "Password ändern");
		putValue(SHORT_DESCRIPTION, "zeigt den Dialog zum Ändern des Login-Passworts an");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationController appController = ApplicationController.getInstance();
		ApplicationData appData = ApplicationData.getInstance();
		User user = appData.getCurrentUser();

		IUserController userController = null;
		if(user.getType() == User.TYPE_CUSTOMER) {
			userController = appController.getCustomerController();
		} else {
			userController = appController.getEmployeeController();
		}
				
		JDialogChangePassword dialog = new JDialogChangePassword(user, userController);
		
		dialog.setTitle("Eigenes Profil bearbeiten");
		dialog.setSize(200, 400);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}
}
