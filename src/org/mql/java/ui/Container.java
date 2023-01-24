package org.mql.java.ui;


import java.awt.event.AdjustmentListener;
import javax.swing.JScrollPane;
import org.mql.java.ui.controller.PaintOnScroll;


public class Container extends JScrollPane {


	private static final long serialVersionUID = 1L;

	public Container(ClassSketch sketch) {
		super(sketch);
		 AdjustmentListener listener = new PaintOnScroll(this);
		 this.getHorizontalScrollBar().addAdjustmentListener(listener);
		 this.getVerticalScrollBar().addAdjustmentListener(listener);

	}
}
