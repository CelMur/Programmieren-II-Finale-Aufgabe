package de.jprojekt.view.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.jprojekt.main.Gui;
import de.jprojekt.view.EmployeeMenuBar;

public class MainFrame extends JFrameAdapter implements ActionListener{

	
	private Gui app;
	private JPanel centerPanel;
	
	
	public MainFrame(Gui app) {
		this.app = app;
		
		initialize();
	}
	
	private void initialize() {
		
		setJMenuBar(new EmployeeMenuBar(this));
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 450);
		
		
		
		
		
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.LIGHT_GRAY);
		
		add(centerPanel, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

		
}
