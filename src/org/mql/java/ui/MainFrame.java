package org.mql.java.ui;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		setContentPane(new Container(new ClassSketch()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();		
		setSize(1200, 720);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
