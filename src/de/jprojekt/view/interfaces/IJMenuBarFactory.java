package de.jprojekt.view.interfaces;

import javax.swing.JMenuBar;

import de.jprojekt.main.ApplicationData;

public interface IJMenuBarFactory {
	public JMenuBar createMenuBar(ApplicationData data);
}
