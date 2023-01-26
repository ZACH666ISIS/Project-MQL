package org.mql.java.models;

import java.util.List;

import org.mql.java.enums.EModifiers;
import org.mql.java.enums.Visibility;


public class EnumModel{
	

	private long id;
	private String name,
				   simpleName;
	private Visibility visibility;
	private EModifiers modificator;
	private List<String> elements;
	private List<InterfaceModel> implementedEnums;

	
	public EnumModel(long id) {
		this.id=id;
	}


	public EnumModel(long id, String name, String simpleName,  Visibility visibility,
			EModifiers modificator,
			List<InterfaceModel> implementedEnums,
			List<String> elements) {
		this.id=id;
		this.name=name;
		this.simpleName=simpleName;
		this.visibility =visibility;
		this.modificator = modificator;
		this.implementedEnums = implementedEnums;
		this.elements = elements;
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


	public List<String> getElements() {
		return elements;
	}


	public void setElements(List<String> elements) {
		this.elements = elements;
	}

	public List<InterfaceModel> getImplementedEnums() {
		return implementedEnums;
	}


	public void setImplementedEnums(List<InterfaceModel> implementedEnums) {
		this.implementedEnums = implementedEnums;
	}


}
