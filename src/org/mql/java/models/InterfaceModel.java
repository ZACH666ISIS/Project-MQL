package org.mql.java.models;

import java.util.List;

import org.mql.java.enums.EModifiers;
import org.mql.java.enums.Visibility;


public class InterfaceModel {
	
	private long id;
	private String name,
				   simpleName;
	private Visibility visibility;
	private EModifiers modificator;
	private List<InterfaceModel> extentedInterface;
	private List<MethodModel> methods;
	
	
	public InterfaceModel(int id, List<InterfaceModel> extentedInterface) {
		this.id=id;
		this.extentedInterface = extentedInterface;
	}
	public InterfaceModel(long id, String name, String simpleName, Visibility visibility,
			EModifiers modificator) {
		this.id=id;
		this.name=name;
		this.simpleName=simpleName;
		this.visibility =visibility;
		this.modificator = modificator;
	}
	
	public InterfaceModel(long id, String name, String simpleName, Visibility visibility,
			EModifiers modificator,
			List<InterfaceModel> extentedInterface , List<MethodModel> methods) {
		this.id=id;
		this.name=name;
		this.simpleName=simpleName;
		this.visibility =visibility;
		this.modificator = modificator;
		this.extentedInterface = extentedInterface;
		this.methods = methods;
	}
	public InterfaceModel(long id, String name, String simpleName, Visibility visibility,
			EModifiers modificator,
			 List<MethodModel> methods) {
		this.id=id;
		this.name=name;
		this.simpleName=simpleName;
		this.visibility =visibility;
		this.modificator = modificator;
		this.methods = methods;
	}


	public InterfaceModel(int id, List<InterfaceModel> extentedInterface , List<MethodModel> methods) {
		this.id = id;
		this.extentedInterface = extentedInterface;
		this.methods = methods;
	}


	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public EModifiers getModificator() {
		return modificator;
	}
	public List<InterfaceModel>  getExtentedInterface() {
		return extentedInterface;
	}


	public void setExtentedInterface(List<InterfaceModel>  extentedInterface) {
		this.extentedInterface = extentedInterface;
	}


	public List<MethodModel> getMethods() {
		return methods;
	}


	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}
	
	
	
	

}
