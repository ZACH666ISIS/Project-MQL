package org.mql.java.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import org.mql.java.ui.models.InterfaceData;
import org.mql.java.ui.models.PointLiaison;
import org.mql.java.ui.models.Propertie;

public class InterfacePainter implements Painter{

	
	private String name;
	private long id;
	private List<Propertie> methods;
	private int maxWidth, maxHeight;
	private int x, y;
	private PointLiaison liaison;
	
	public InterfacePainter(InterfaceData intr, PrintedArea area) {
		name = intr.getName();
		id = intr.getId();
		methods = intr.getMethods();
		setMaxLength();
		this.x=area.getX();
		this.y=area.getY();
		area.set(maxWidth, 50 + maxHeight);
		liaison = new PointLiaison(x,y, maxWidth, maxHeight);
	}
	
	private void setMaxLength() {
		maxWidth = methods.stream().max((o1, o2) -> o1.value.length()-o2.value.length() ).get().value.length() * 7;
		maxHeight = 120 + methods.size()*20;
	}
	
	
	public void paint(Graphics g) {
		int cursor = y;
		g.drawRect(x,y, maxWidth, maxHeight);
		cursor +=80;
	    g.drawLine(x, cursor, x+maxWidth, cursor);
		printMethods(g);
		printName(g);
	}
	
	private void printName(Graphics g) {
		int center = maxWidth/2;
		g.setFont(new Font("Helvetica", Font.BOLD, 18));
		center -= (g.getFontMetrics(g.getFont()).stringWidth("<<Interface>>"))/2;
		int pos = y+g.getFontMetrics().getHeight();
		g.drawString("<<Interface>>", x+center, pos );
		center = maxWidth/2- (g.getFontMetrics(g.getFont()).stringWidth(name))/2;
		g.drawString(name, x+center, pos + g.getFontMetrics().getHeight());
	}
	
	private void printMethods(Graphics g) {
		g.setColor(Color.BLACK);
		int my =y+80+g.getFontMetrics().getHeight();
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
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
