package org.mql.java.ui.models;

import demo.mql.java.enums.Visibility;

public class ClassElement {
	
	private String content;
	private boolean isStatic;
	private Visibility visibility;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	
	

}
