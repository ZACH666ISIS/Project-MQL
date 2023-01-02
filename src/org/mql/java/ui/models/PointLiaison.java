package org.mql.java.ui.models;

public class PointLiaison {
	
	private int xLeft,yLeft,
				xRigth,yRigth,
				xTop,yTop,
				xBottom,yBottom;

	public PointLiaison(int x,int y ,int width,int height) {
		super();
		setValues(x,y,width,height);
	}

	private void setValues(int x ,int y ,int width,int height) {
		xLeft = x;
		yLeft = (y+height)/2;
		xRigth = (x+width);
		yRigth = (y+height)/2;
		xTop = x/2;
		yTop = y;
		xBottom = x/2;
		yBottom = y;
		
	}
	
	public int getXLeft() {
		return xLeft;
	}

	public int getYLeft() {
		return yLeft;
	}

	public int getXRigth() {
		return xRigth;
	}

	public int getYRigth() {
		return yRigth;
	}

	public int getXTop() {
		return xTop;
	}

	public int getYTop() {
		return yTop;
	}

	public int getXBottom() {
		return xBottom;
	}

	public int getYBottom() {
		return yBottom;
	}

	

}
