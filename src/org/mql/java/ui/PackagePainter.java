package org.mql.java.ui;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PackageData;

public class PackagePainter {
	
	private String packageName;
	private List<ClassData> classes;
	private List<ClassPainter> clsPainter;
	private PrintedArea area;
	
	
	/**
	 * 
	 * charger les données men xml f model de data
	 * 
	 */
	
	public PackagePainter(PackageData p,PrintedArea area) {
		this.packageName = p.getPackageName();
		this.classes = p.getClasses();
		this.area = area;
		clsPainter = new Vector<>();
	}
	
	
	
	public void paintPackage(Graphics g) {
		int x0 = area.getX() - 15,
			y0 = area.getY() - 15;
	
		ClassPainter cp;
		for(ClassData cls : classes) {
			cp = new ClassPainter(cls, area);
			cp.paintClass(g);
			clsPainter.add(cp);
	
		}
		g.drawString(packageName, x0, y0);
		g.drawRect(x0 , y0 , area.getMaxX() + 10,  area.getMaxY()-y0 +10 );
		
	}
	
	
	public List<ClassPainter> getClasses(){
		return clsPainter;
	}
	
	
	
	

}
