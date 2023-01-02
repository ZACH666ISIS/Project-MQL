package org.mql.java.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.List;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PointLiaison;

public class ClassPainter {
	
	private String className;
	private List<String> fields;
	private List<String> methods;
	private int maxHeightFields,
				maxHeightMethods,
				maxWidth;
	private int x, y;
	private PointLiaison liaison;
	private PrintedArea area;
	private int lineX,lineY;

	
	public ClassPainter(ClassData cls,PrintedArea area) {
		this.className=cls.getClassName();
		this.fields=cls.getFields();
		this.methods=cls.getMethods();
		setMaxLength();
		this.x=area.getX();
		this.y=area.getY();
		area.set(maxWidth, 70 + maxHeightMethods + maxHeightFields);
		liaison = new PointLiaison(x,y, maxWidth, 70 + maxHeightMethods + maxHeightFields);
	

	}
	

	
	private void setMaxLength() {
		maxWidth = methods.stream().max(Comparator.
                comparing(String::length)).get().length() * 7;
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
		for (String f : fields)
		       g.drawString(f, x+10, fy += g.getFontMetrics().getHeight());

	}
	private void printMethods(Graphics g) {
		g.setColor(Color.BLACK);
		int my =y+50+maxHeightFields+g.getFontMetrics().getHeight();
		for (String f : methods) {
			 g.drawString(f, x+10, my += g.getFontMetrics().getHeight());
		}
	}

	public PointLiaison getLiaison() {
		return liaison;
	}




	
	
	
}
