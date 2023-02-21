package org.mql.java.models;

import org.mql.java.models.Association;
import org.mql.java.models.assotiation.AssociationType;
import org.mql.java.models.assotiation.Values;

public class Association {
	
	private long id;
	private long son;
	private long parent;	
	private Values sonValue;
	private Values parentValue;
	private AssociationType type;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSon() {
		return son;
	}
	public void setSon(long son) {
		this.son = son;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	public Values getSonValue() {
		return sonValue;
	}
	public void setSonValue(Values sonValue) {
		this.sonValue = sonValue;
	}
	public Values getParentValue() {
		return parentValue;
	}
	public void setParentValue(Values parentValue) {
		this.parentValue = parentValue;
	}
	public AssociationType getType() {
		return type;
	}
	public void setType(AssociationType type) {
		this.type = type;
	}

	
	
}
