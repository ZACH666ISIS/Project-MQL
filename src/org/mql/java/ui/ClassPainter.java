package org.mql.java.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PointLiaison;
import org.mql.java.ui.models.Propertie;

public class ClassPainter {
	
	private long id;
	private String className;
	private List<Propertie> fields;
	private List<Propertie> methods;
	private int maxHeightFields,
				maxHeightMethods,
				maxWidth;
	private int x, y;
	private PointLiaison liaison;


	
	public ClassPainter(ClassData cls,PrintedArea area) {
		this.className=cls.getClassName();
		this.id = cls.getId();
		this.fields=cls.getFields();
		this.methods=cls.getMethods();
		setMaxLength();
		this.x=area.getX();
		this.y=area.getY();
		area.set(maxWidth, 120 + maxHeightMethods + maxHeightFields);
		liaison = new PointLiaison(x,y, maxWidth, 70 + maxHeightMethods + maxHeightFields);
	}
	

	
	private void setMaxLength() {
		maxWidth = methods.stream().max((o1, o2) -> o1.value.length()-o2.value.length() ).get().value.length() * 7;
		maxHeightFields = fields.size()*20;
		maxHeightMethods = methods.size()*20;
	}
	
	public void paintClass(Graphics g) {
		int cursor = y;
		g.setColor(Color.BLACK);
        g.drawRect(x,y, maxWidth, 70 + maxHeightMethods + maxHeightFields);
        cursor +=50;
        g.drawLine(x, cursor, x+maxWidth, cursor);
        cursor += maxHeightFields+10;
        g.drawLine(x,  cursor , x+maxWidth, cursor);
        printClass(g);
        printFields(g);
        printMethods(g);
	}
	
	private void printClass(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", Font.BOLD, 18));
		g.drawString(className, x+10, y+g.getFontMetrics().getHeight());

	}
	private void printFields(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		
		int fy =y+40+g.getFontMetrics().getHeight();
		for (Propertie f : fields) {
			g.drawString(f.value, x+10, fy += g.getFontMetrics().getHeight());
			if(f.isStatic) {
				g.drawLine(x+10, fy + 2, x + g.getFontMetrics(g.getFont()).stringWidth(f.value)+6, fy + 2);
			}
		}
			
			

	}
	private void printMethods(Graphics g) {
		g.setColor(Color.BLACK);
		int my =y+50+maxHeightFields+g.getFontMetrics().getHeight();
		for (Propertie m : methods) {
			g.drawString(m.value, x+10, my += g.getFontMetrics().getHeight());
			if(m.isStatic) {
				g.drawLine(x+10, my + 2, x + g.getFontMetrics(g.getFont()).stringWidth(m.value)+6, my + 2);
			}
		}
	}
	


	public long getId() {
		return id;
	}
	
	public PointLiaison getLiaison() {
		return liaison;
	}




	
	
	
}
