package de.jprojekt.view;

import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.jprojekt.main.ApplicationGui;
import de.jprojekt.view.actions.*;
import de.jprojekt.view.frames.JFrameAdapter;

public class EmployeeMenuBar extends JMenuBar{
	
	
	private final AbstractAction actNewCustomer;
	private final AbstractAction actNewEmployee;
	private final AbstractAction actLogout;
	private final AbstractAction actEditEmployee;
	private final AbstractAction actEditCustomer;
	private final AbstractAction actResetPassword;
	private final AbstractAction actShowBankAccounts;
	
	private JFrame frame;
	
	public EmployeeMenuBar(ApplicationGui gui, JFrameAdapter frame) {
		this.frame = frame;
		
		actNewCustomer = new ActionNewCustomer(frame);
		actEditCustomer = new ActionEditCustomer(frame);
		actNewEmployee = new ActionNewEmployee(frame);
		actEditEmployee = new ActionEditEmployee(frame);
		actResetPassword = new ActionChangePassword(frame);
		actShowBankAccounts = new ActionShowBankingAccountsEmployee(frame);
		
		actLogout = new ActionLogout(gui, frame);
		
		
		initialize();
	}
	
	private void initialize() {
	
		add(createMenu());
		add(createAccountMenu());
		add(createReportMenu());
		
		
	}
	
	private JMenu createMenu() {
		JMenu menu = new JMenu("Banker Men?");
		
		menu.add(createMenuItem(actNewCustomer));
		menu.add(createMenuItem(actEditCustomer));
		menu.add(createMenuItem(actNewEmployee));
		
		
		return menu;
	}
	
	private JMenu createAccountMenu() {
		JMenu menu = new JMenu("Account");
		
		menu.add(createMenuItem(actEditEmployee));
		menu.add(createMenuItem(actResetPassword));
		menu.add(createMenuItem(actLogout));
		
		return menu;
	}
	
	
	private JMenu createReportMenu() {
		JMenu menu = new JMenu("Reports");
		menu.add(createMenuItem(actShowBankAccounts));
		
		return menu;
	}
	
	private JMenuItem createMenuItem(AbstractAction a) {
		return new JMenuItem(a); 		
	}
	
	
}
