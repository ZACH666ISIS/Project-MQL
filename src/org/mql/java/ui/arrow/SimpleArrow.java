package org.mql.java.ui.arrow;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class SimpleArrow implements Arrow{

	public void draw(Graphics g, int x1, int y1, int x2, int y2) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Line2D.Float(x1, y1, x2, y2));
        g2.setStroke(new BasicStroke(1));
	}

}
