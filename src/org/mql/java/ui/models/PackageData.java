package org.mql.java.ui.models;

import java.util.List;
import java.util.Vector;

public class PackageData {
	
	private long id;
	private String packageName;
	private List<ClassData> classes;
	private List<InterfaceData> interfaces;
	private List<EnumData>	enumes;
	
	public PackageData(long id) {
		super();
		this.id = id;
		classes = new Vector<>();
		interfaces = new Vector<>();
		enumes = new Vector<>();
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
	public List<InterfaceData> getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(List<InterfaceData> interfaces) {
		this.interfaces = interfaces;
	}
	public List<EnumData> getEnumes() {
		return enumes;
	}
	public void setEnumes(List<EnumData> enumes) {
		this.enumes = enumes;
	}
	



	
}
