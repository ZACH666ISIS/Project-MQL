package org.mql.java.ui;

import java.awt.Graphics;

import org.mql.java.ui.models.PointLiaison;

public interface Painter {

	public void paint(Graphics g);
	public long getId();
	public PointLiaison getLiaison();
}
