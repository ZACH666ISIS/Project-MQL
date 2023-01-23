package org.mql.java.ui.models;

import java.awt.Point;

public class PointLiaison {
	
	private Point left,
				  right,
				  top,
				  bottom;

	public PointLiaison(int x,int y ,int width,int height) {
		super();
		left = new Point();
		right = new Point();
		top =  new Point();
		bottom = new Point();
		setValues(x,y,width,height);
	}

	private void setValues(int x ,int y ,int width,int height) {
		left.x = x;
		left.y = (y+height)/2;
		right.x = (x+width);
		right.y = (y+height)/2;
		top.x = x/2;
		top.y = y;
		bottom.x = x/2;
		bottom.y = y;
		
	}

	public Point getLeft() {
		return left;
	}

	public void setLeft(Point left) {
		this.left = left;
	}

	public Point getRight() {
		return right;
	}

	public void setRight(Point right) {
		this.right = right;
	}

	public Point getTop() {
		return top;
	}

	public void setTop(Point top) {
		this.top = top;
	}

	public Point getBottom() {
		return bottom;
	}

	public void setBottom(Point bottom) {
		this.bottom = bottom;
	}
	


	

}
