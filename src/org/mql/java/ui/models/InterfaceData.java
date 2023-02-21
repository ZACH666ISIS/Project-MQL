package org.mql.java.ui.models;

import java.util.List;

public class InterfaceData {
	
	private long id;
	private String name;
	private List<Propertie> methods;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Propertie> getMethods() {
		return methods;
	}
	public void setMethods(List<Propertie> methods) {
		this.methods = methods;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
