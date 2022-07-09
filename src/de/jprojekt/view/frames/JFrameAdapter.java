package de.jprojekt.view.frames;

import javax.swing.JFrame;

public abstract class JFrameAdapter extends JFrame{
	
	public JFrameAdapter() {}
	
	public JFrameAdapter(String name) {
		super(name);
	}

	public abstract Object getData();
}
