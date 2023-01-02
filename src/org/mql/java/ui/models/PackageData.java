package org.mql.java.ui.models;

import java.util.List;

public class PackageData {
	
	private String packageName;
	private List<ClassData> classes;
	
	public PackageData() {
		super();
	}
	
	
	public PackageData(String packageName, List<ClassData> classes) {
		super();
		this.packageName = packageName;
		this.classes = classes;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<ClassData> getClasses() {
		return classes;
	}
	public void setClasses(List<ClassData> classes) {
		this.classes = classes;
	}

	
}
