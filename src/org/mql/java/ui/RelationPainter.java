package org.mql.java.ui;

import java.awt.Graphics;
import java.awt.Point;

import org.mql.java.models.assotiation.AssociationType;
import org.mql.java.ui.arrow.Arrow;
import org.mql.java.ui.arrow.ImplArrow;
import org.mql.java.ui.arrow.InheritanceArrow;
import org.mql.java.ui.arrow.SimpleArrow;
import org.mql.java.ui.models.PointLiaison;

public class RelationPainter {
	
	
	private PointLiaison p1,p2;
	private Point rp1,rp2;
	
	public RelationPainter() {
	}
	
	public RelationPainter(Painter c1, Painter c2) {
		this.p1 = c1.getLiaison();
		this.p2 = c2.getLiaison();
		verifyPosition();
	}
	
	private void verifyPosition() {
		int refX1 = p1.getLeft().x,
			refX2 = p2.getLeft().x,
			refY1 = p1.getTop().y,
			refY2 = p2.getTop().y;
		if((refX1 - refX2 < 0) && (refY1 - refY2) <0) {
			rp1 = p1.getRight();
			rp2 = p2.getLeft();
		}
		else if((refX1 - refX2 > 0) && (refY1 - refY2) >0) {
			rp1 = p1.getLeft();
			rp2 = p2.getRight();
		}
		else if((refX1 - refX2 < 0) && (refY1 - refY2) >0) {
			rp1 = p1.getLeft();
			rp2 = p2.getBottom();
		}
		else if((refX1 - refX2 > 0) && (refY1 - refY2) <0) {
			rp1 = p1.getBottom();
			rp2 = p2.getRight();
		}
		else if((refX1 - refX2 == 0) && (refY1 - refY2) <0) {
			rp1 = p1.getBottom();
			rp2 = p2.getTop();
		}
		else if((refX1 - refX2 == 0) && (refY1 - refY2) >0) {
			rp1 = p1.getTop();
			rp2 = p2.getBottom();
		}
		else if((refX1 - refX2 > 0) && (refY1 - refY2) == 0) {
			rp1 = p1.getLeft();
			rp2 = p2.getRight();
		}
		else if((refX1 - refX2 < 0) && (refY1 - refY2) == 0) {
			rp1 = p1.getRight();
			rp2 = p2.getLeft();
		}
		// else same class (thorw error)

	}

	public void etablishRelation(Graphics g,AssociationType type) {
		Arrow a;
		if(type == AssociationType.INHERITANCE) {
			a = new InheritanceArrow();
		}
		else if(type == AssociationType.IMPLEMENTATION) {
			a = new ImplArrow();
		}
		else {
			a = new SimpleArrow();
		}
		a.draw(g, rp1.x, rp1.y, rp2.x, rp2.y);
	}
}
