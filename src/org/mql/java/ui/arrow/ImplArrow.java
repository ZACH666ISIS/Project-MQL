package org.mql.java.ui.arrow;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class ImplArrow implements Arrow {

	public void draw(Graphics g, int x1, int y1, int x2, int y2) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(new Triangle(x1,y1,x2,y2).drawArrowHead());
		Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{7}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1+15, x2, y2);
        g2d.setStroke(new BasicStroke(1));
	}

}
