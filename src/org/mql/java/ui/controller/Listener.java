package org.mql.java.ui.controller;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Listener implements AdjustmentListener{
	
	private JPanel pane;
	JFrame frame;

	public Listener(JPanel pane ,JFrame frame) {
		this.pane =pane;
	}



	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		pane.revalidate();
		pane.repaint();
		
	}
}