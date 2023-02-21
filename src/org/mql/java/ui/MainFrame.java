package org.mql.java.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setContentPane(new Container(new ClassSketch()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();		
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
