package org.mql.java.ui;

import java.awt.Dimension;

public class PrintedArea {

	private int refXe,refYe,
				height,width;
	private int maxYe,maxXe;
	private Dimension dim;
	
	public PrintedArea(int refXe, int refYe , Dimension dim) {
		this(refXe,refYe,dim,0,0);
		this.refXe = refXe;
		this.refYe = refYe;
		this.dim = dim;
	}
	public PrintedArea(int refXe, int refYe , Dimension dim ,int height,int width) {
		super();
		this.maxXe = 0;
		this.maxYe = 0;
		this.height = height;
		this.width = width;
		this.refXe = refXe;
		this.refYe = refYe;
		this.dim = dim;
	}
	

	public void set(int width,int height) {
		refXe += width + 20;
		if((height + refYe ) > maxYe ) maxYe = (height + refYe );
		if((width + refXe) > maxXe ) maxXe = refXe + width ;
		
		if(refXe >= dim.width) {
			maxXe = refXe;
			refXe = 40;
			refYe = maxYe;
		}
		
		
	}
	
	public void newPackage(int refXe, int refYe) {
		this.refXe = refXe;
		this.refYe = refYe;
		maxXe = 0;
		maxYe = 0;
	}
	public boolean isVisible(int width ,int heigth) {
		if(width>=dim.width) return true;
		return false;
	}
	
	



	public void setX(int refXe) {
		this.refXe = refXe;
	}

	public void setY(int refYe) {
		this.refYe = refYe;
	}
	public int getX() {
		return refXe;
	}


	public int getY() {
		return refYe;
	}
	
	public int getMaxY() {
		return maxYe;
	}

	public int getMaxX() {
		return maxXe;
	}

}
