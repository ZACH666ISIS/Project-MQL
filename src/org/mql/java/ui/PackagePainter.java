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
	
	public PackagePainter(PackageData p,PrintedArea area) {
		this.packageName = p.getPackageName();
		this.classes = p.getClasses();
		this.area = area;
	}
	
	
	
	public void paintPackage(Graphics g) {
		int x0 = area.getX() - 15,
			y0 = area.getY() - 15;

		PointLiaison p1 = null,p2= null ;
	
		ClassPainter cp;
		int i=0;
		for(ClassData cls : classes) {
			cp = new ClassPainter(cls, area);
			if(i==0) {
				p1 = cp.getLiaison();
			}
			if(i==2) {
				p2 = cp.getLiaison();
			}
			cp.paintClass(g);
			i++;
		}

		g.drawLine(p1.getXRigth(), p1.getYRigth(),p2.getXLeft(),p2.getYLeft());
		g.setColor(Color.red);
		g.drawRect(x0 , y0 , area.getMaxX() + 10, area.getMaxY() +10 );
		
	}
	
	
	
	

}
