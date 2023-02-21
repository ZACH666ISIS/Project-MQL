package org.mql.java.models;


import java.util.List;

import org.mql.java.enums.EModifiers;
import org.mql.java.enums.Visibility;


public class ClassModel {

	private long id;
	private String name,
				   simpleName;
	private Visibility visibility;
	private EModifiers modificator;
	private List<Attribute> attributes;
	private List<ConstructorModel> constructors;
	private List<MethodModel> methods;
	private List<InterfaceModel> implemented;
	private ClassModel extended;
	
	
	public ClassModel(long id) {
		this.id =id;
	}


	
	public ClassModel(long id, String name, String simpleName, Visibility visibility,
			EModifiers modificator) {
		this.id=id;
		this.name=name;
		this.simpleName=simpleName;
		this.visibility =visibility;
		this.modificator = modificator;
	}



	public ClassModel(long id, List<Attribute> attribute, List<ConstructorModel> constructors, List<MethodModel> methods,
			List<InterfaceModel> implemented,
			ClassModel extended) {
		this.id=id;
		this.attributes = attribute;
		this.constructors = constructors;
		this.methods = methods;
		this.implemented = implemented;
		this.extended = extended;

	}

	public ClassModel(  long id, 
						String name, 
						String simpleName,
						Visibility visibility,
						EModifiers modificator,
						List<Attribute> attribute,
						List<ConstructorModel> constructors,
						List<MethodModel> methods,
						List<InterfaceModel> implemented,
						ClassModel extended) {
		this.id=id;
		this.name=name;
		this.simpleName=simpleName;
		this.visibility =visibility;
		this.modificator = modificator;
		this.attributes = attribute;
		this.constructors = constructors;
		this.methods = methods;
		this.implemented = implemented;
		this.extended = extended;
	}


	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSimpleName() {
		return simpleName;
	}



	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}



	public Visibility getVisibility() {
		return visibility;
	}



	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}



	public EModifiers getModificator() {
		return modificator;
	}



	public void setModificator(EModifiers modificator) {
		this.modificator = modificator;
	}



	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attributes = attribute;
	}
	public List<ConstructorModel> getConstructors() {
		return constructors;
	}
	public void setConstructors(List<ConstructorModel> constructors) {
		this.constructors = constructors;
	}
	public List<MethodModel> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}

	public List<InterfaceModel> getImplemented() {
		return implemented;
	}
	public void setImplemented(List<InterfaceModel> implemented) {
		this.implemented = implemented;
	}

	public ClassModel getExtended() {
		return extended;
	}
	public void setExtended(ClassModel extended) {
		this.extended = extended;
	}

	public String toString() {
		return "ClassModel [id=" + id + ", name=" + name + ", simpleName=" + simpleName + ", extended=" + extended
				+ "]";
	}

	
	
}
