package org.mql.java.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.List;

import org.mql.java.ui.models.EnumData;
import org.mql.java.ui.models.PointLiaison;

public class EnumPainter implements Painter{
	
	private String name;
	private long id;
	private List<String> elements;
	private int maxWidth, maxHeight;
	private int x, y;
	private PointLiaison liaison;
	
	public EnumPainter(EnumData enumData,PrintedArea area) {
		name = enumData.getName();
		id = enumData.getId();
		elements = enumData.getElements();
		setMaxLength();
		this.x=area.getX();
		this.y=area.getY();
		area.set(maxWidth, 50 + maxHeight);
		liaison = new PointLiaison(x,y, maxWidth, maxHeight);
	}
	
	private void setMaxLength() {
		maxWidth = elements.stream().max(Comparator.comparing( String::length )).get().length() * 10;
		maxHeight = 120 + elements.size()*20;
	}
	
	
	public void paint(Graphics g) {
		int cursor = y;
		g.drawRect(x,y, maxWidth, maxHeight);
		cursor +=80;
	    g.drawLine(x, cursor, x+maxWidth, cursor);
		printElements(g);
		printName(g);
	}
	
	private void printName(Graphics g) {
		int center = maxWidth/2;
		g.setFont(new Font("Helvetica", Font.BOLD, 18));
		int pos = y+g.getFontMetrics().getHeight();
		center -= (g.getFontMetrics(g.getFont()).stringWidth("<<Enum>>"))/2;
		g.drawString("<<Enum>>", x+center, pos );
		center = (maxWidth/2) - (g.getFontMetrics(g.getFont()).stringWidth(name))/2;
		g.drawString(name, x+center, pos + g.getFontMetrics().getHeight());
	}
	
	private void printElements(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		int fy =y+65+g.getFontMetrics().getHeight();
		for (String e : elements) {
			g.drawString(e, x+10, fy += g.getFontMetrics().getHeight());		
		}
	}


	public long getId() {
		return id;
	}

	public PointLiaison getLiaison() {
		return liaison;
	}
	
}