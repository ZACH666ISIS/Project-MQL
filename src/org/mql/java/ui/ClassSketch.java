package org.mql.java.ui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import org.mql.java.ui.loader.XMLLoader;
import org.mql.java.ui.models.PackageData;

public class ClassSketch extends JPanel{
	
	
	  private static final long serialVersionUID = 1L;
	
	  private Dimension d;
	  private PrintedArea area;
	  public ClassSketch() {
		
		 d = new Dimension(1600, 0);
		 repaint();				
		
	 }
	 public void paintComponent(Graphics g){
		 area = new PrintedArea(40,40,d);
		 List<PackageData> packages = new Vector<>();
		 XMLLoader loader = new XMLLoader("resources/document.xml");
		 packages.addAll(loader.getPackages());
		 PackagePainter p;
		 
		 for(PackageData packageData : packages) {
			 p = new PackagePainter(packageData, area);
			 p.paintPackage(g);
			 area.newPackage(40,area.getMaxY()+80);
		 }
		
		 setPreferredSize(new Dimension(d.width , area.getDim().height));
	 	 
	 }

	

	 
}
