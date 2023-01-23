package org.mql.java.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PackageData;
import org.mql.java.ui.models.PointLiaison;

public class PackagePainter {
	
	private String packageName;
	private List<ClassData> classes;
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
	}
	
	
	
	public void paintPackage(Graphics g) {
		int x0 = area.getX() - 15,
			y0 = area.getY() - 15;
	
		ClassPainter cp;
		for(ClassData cls : classes) {
			cp = new ClassPainter(cls, area);
			cp.paintClass(g);
	
		}

		g.setColor(Color.getHSBColor((float)Math.random()*100, (float)Math.random()*10, (float)Math.random()*100));
		g.drawRect(x0 , y0 , area.getMaxX() + 10, area.getMaxY() +10 );
		
	}
	
	
	
	

}
