package org.mql.java.ui.models;

import java.util.List;
import java.util.Vector;

public class ClassData {
	
	private long id;
	private String className;
	private List<Propertie> fields;
	private List<Propertie> methods;
	
	public ClassData(long id) {
		super();
		this.id =id;
		fields = new Vector<>();
		methods = new Vector<>();
	}
	


	public ClassData(String className, List<Propertie> fields, List<Propertie> methods) {
		super();
		this.className = className;
		this.fields = fields;
		this.methods = methods;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<Propertie> getFields() {
		return fields;
	}
	public void setFields(List<Propertie> fields) {
		this.fields = fields;
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
