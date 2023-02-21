package org.mql.java.ui.arrow;


import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Triangle extends Polygon{
	private static final long serialVersionUID = 1L;
	private double angle;
	private Point trp;
	
	public Triangle(int x1,int y1,int x2,int y2) {
		addPoint( 0,5);
		addPoint( -5, -5);
		addPoint( 5,-5);
		angle = Math.atan2(y2-y1, x2-x1);
		trp = new Point(x2,y2);
	}
	
	
	public Shape drawArrowHead() {
		AffineTransform tx = new AffineTransform();
	    tx.setToIdentity();
	    tx.translate(trp.x,trp.y);
	    tx.rotate((angle-Math.PI/2d));   
	    return tx.createTransformedShape(this);
	}
	
}
