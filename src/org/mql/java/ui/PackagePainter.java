package org.mql.java.ui;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.EnumData;
import org.mql.java.ui.models.InterfaceData;
import org.mql.java.ui.models.PackageData;

public class PackagePainter {
	
	private String packageName;
	private List<ClassData> classes;
	private List<InterfaceData> interfaces;
	private List<EnumData> enums;
	private List<Painter> clsPainter;
	private PrintedArea area;
	
	
	/**
	 * 
	 * charger les données men xml f model de data
	 * 
	 */
	
	public PackagePainter(PackageData p,PrintedArea area) {
		this.packageName = p.getPackageName();
		this.classes = p.getClasses();
		this.enums = p.getEnumes();
		this.interfaces = p.getInterfaces();
		this.area = area;
		clsPainter = new Vector<>();
	}
	
	
	
	public void paintPackage(Graphics g) {
		int x0 = area.getX() - 15,
			y0 = area.getY() - 15;
	
		Painter p;
		for(ClassData cls : classes) {
			p = new ClassPainter(cls, area);
			p.paint(g);
			clsPainter.add(p);
	
		}
		for(EnumData enm : enums) {
			p = new EnumPainter(enm, area);
			p.paint(g);
			clsPainter.add(p);
		}
		for(InterfaceData intr : interfaces) {
			p = new InterfacePainter(intr,area);
			p.paint(g);
			clsPainter.add(p);
		}
		
		g.drawString(packageName, x0, y0);
		g.drawRect(x0 , y0 , area.getMaxX() + 10,  area.getMaxY()-y0 +10 );
		
	}
	
	
	public List<Painter> getPainters(){
		return clsPainter;
	}
	
	
	
	

}
