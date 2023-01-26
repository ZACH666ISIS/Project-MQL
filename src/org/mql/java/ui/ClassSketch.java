package org.mql.java.ui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import org.mql.java.ui.loader.XMLLoader;
import org.mql.java.ui.models.PackageData;
import org.mql.java.ui.models.Relation;

public class ClassSketch extends JPanel{
	
	
	  private static final long serialVersionUID = 1L;
	
	  private Dimension d;
	  private PrintedArea area;
	  private List<Painter> classes;
	  public ClassSketch() {
		
		 d = new Dimension(1600, 0);
		 classes = new Vector<>();
		repaint();
	 }
	 public void paintComponent(Graphics g){
		 area = new PrintedArea(40,40,d);
		 List<PackageData> packages ;
		 List<Relation> relations;
		 XMLLoader loader = new XMLLoader("resources/document.xml");
		 packages = loader.getPackages();
		 relations = loader.getRelations();
		 PackagePainter p;
		 
	
		 for(PackageData packageData : packages) {
			 p = new PackagePainter(packageData, area);
			 p.paintPackage(g);
			 area.newPackage(40,area.getMaxY()+80);
			 classes.addAll(p.getPainters());
		 }
		 
		 for(Relation r : relations) {
			 Painter cp1 = getClassPaint(r.getId1()),
					      cp2 = getClassPaint(r.getId2());
			 if(cp1 != null && cp2 != null) {
				 RelationPainter rp = new RelationPainter(cp1, cp2);
				 rp.etablishRelation(g);
			 }
		 }
		 setPreferredSize(new Dimension(d.width , area.getDim().height + 50));
	 	 
	 }

	private Painter getClassPaint(long id) {
		for(Painter cp : classes) {
			if(cp.getId() == id)
			return cp;
		}
		return null;
	}

	 
}
