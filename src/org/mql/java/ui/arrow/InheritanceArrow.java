package org.mql.java.ui.arrow;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class InheritanceArrow implements Arrow{

	public void draw(Graphics g, int x1 ,int y1, int x2, int y2) {
		Triangle t = new Triangle(x1,y1,x2,y2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.fill(t.drawArrowHead());
		new SimpleArrow().draw(g, x1, y1+15, x2, y2);
	}

}
