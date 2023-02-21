package org.mql.java.ui.controller;


import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollPane;

/*
 * Repainting onscrolling
 */
public class PaintOnScroll implements AdjustmentListener{
	private JScrollPane pane;
	public PaintOnScroll(JScrollPane pane) {
		this.pane = pane;
	}
	public void adjustmentValueChanged(AdjustmentEvent evt) {
	    pane.repaint();
	 }
}