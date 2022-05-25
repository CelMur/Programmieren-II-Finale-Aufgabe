package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.jprojekt.view.interfaces.IActionDataProvider;
import de.jprojekt.view.interfaces.ICommand;

public class DefaultAction <R, T> extends AbstractAction{
	
	private IActionDataProvider<T> provider;
	private ICommand<R,T> command;
	
	
	public DefaultAction() {
		
	}
	
	public DefaultAction(ICommand<R,T> command, IActionDataProvider<T> provider) {
		this.command = command;
		this.provider = provider;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(provider == null) throw new NullPointerException();
		if(command == null) throw new NullPointerException();
		
		command.execute(provider.getData());
	}
	
	
	protected void setProvider(IActionDataProvider<T> provider) {
		this.provider = provider;
	}
	
	protected void setCommand(ICommand<R,T> command) {
		this.command = command; 
	}
	
}
