package de.jprojekt.view;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.jprojekt.view.actions.ActionDepositMoney;
import de.jprojekt.view.actions.ActionEditCustomer;
import de.jprojekt.view.actions.ActionLogout;
import de.jprojekt.view.actions.ActionNewBankAccount;
import de.jprojekt.view.actions.ActionResetPassword;
import de.jprojekt.view.actions.ActionTransferMoney;
import de.jprojekt.view.actions.ActionWithdrawMoney;
import de.jprojekt.view.frames.JFrameAdapter;

public class CustomerMenuBar extends JMenuBar{
	
	private JFrame frame;
	
	private final AbstractAction actLogout;
	private final AbstractAction actDepostiMoney;
	private final AbstractAction actWithdrawMoney;
	private final AbstractAction actResetPassword;
	private final AbstractAction actTransferMoney;
	private final AbstractAction actNewBankAccount;
	private final AbstractAction actEditCustomer;
	
	public CustomerMenuBar(JFrameAdapter frame) {
		this.frame = frame;
		
		actLogout = new ActionLogout(frame);
		actDepostiMoney = new ActionDepositMoney(frame);
		actEditCustomer = new ActionEditCustomer(frame);
		actNewBankAccount = new ActionNewBankAccount(frame);
		actResetPassword = new ActionResetPassword(frame);
		actTransferMoney = new ActionTransferMoney(frame);
		actWithdrawMoney = new ActionWithdrawMoney(frame);
		
		initialize();
	}
	
	private void initialize() {
		add(createBankAccountMenu());
	}
	
	private JMenu createBankAccountMenu() {
		JMenu menu = new JMenu("Konten");
		
		menu.add(createMenuItem(actNewBankAccount));
		menu.add(createMenuItem(actTransferMoney));
		menu.add(createMenuItem(actWithdrawMoney));
		menu.add(createMenuItem(actDepostiMoney));
		
		return menu;
	}
	
	private JMenu createAccoutMenu() {
		JMenu menu = new JMenu("Account");
		
		menu.add(createMenuItem(actEditCustomer));
		menu.add(createMenuItem(actResetPassword));
		menu.add(createMenuItem(actLogout));
		
		return menu;
	}
	
	private JMenuItem createMenuItem(AbstractAction a) {
		return new JMenuItem(a); 		
	}
}
