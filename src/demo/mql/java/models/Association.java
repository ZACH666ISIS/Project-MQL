package demo.mql.java.models;

import demo.mql.java.models.Association;
import demo.mql.java.models.assotiation.AssotiationType;
import demo.mql.java.models.assotiation.Values;

public class Association {
	
	private long id;
	private long son;
	private long parent;	
	private Values sonValue;
	private Values parentValue;
	private AssotiationType type;
	
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
	public AssotiationType getType() {
		return type;
	}
	public void setType(AssotiationType type) {
		this.type = type;
	}

	
	
}
