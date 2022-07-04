package de.jprojekt.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.jprojekt.view.GuiMessages;
import de.jprojekt.view.frames.JFrameAdapter;

public abstract class AbstractActionAdapter extends AbstractAction{
	
	private JFrameAdapter frame;
	
	public AbstractActionAdapter() {
		
	}
	
	public AbstractActionAdapter(JFrameAdapter frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(frame != null) {
			JOptionPane.showMessageDialog(frame, GuiMessages.NOT_IMPLEMENTED_MSG);
		}
	}
	
	public JFrameAdapter getFrame() {
		return this.frame;
	}

}
