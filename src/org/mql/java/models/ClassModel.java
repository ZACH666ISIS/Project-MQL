package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ClassModel {

	private int id;
	private String name,
				   simpleName;
	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	private ClassType type;
	private Class extended;
	private Package classPackage;
	private List<Field> properties;
	private List<Constructor> constructors;
	private List<Method> methods;
	private List<Class> implemented;
	
	
	public ClassModel() {

	}
		
	public ClassModel(int id) {
		super();
		this.id = id;
	}
	
	public ClassModel(int id,
					  String name,
					  String simpleName,
					  ClassType type,
					  Class extended,
					  Package classPackage,
					  List<Field> properties,
					  List<Constructor> constructors,
					  List<Method> methods,
					  List<Class> implemented) {
		super();
		this.id = id;
		this.name = name;
		this.simpleName = simpleName;
		this.type = type;
		this.extended = extended;
		this.classPackage = classPackage;
		this.properties = properties;
		this.constructors = constructors;
		this.methods = methods;
		this.implemented = implemented;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Package getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(Package classPackage) {
		this.classPackage = classPackage;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ClassType getType() {
		return type;
	}
	public void setType(ClassType type) {
		this.type = type;
	}

	public Class getExtended() {
		return extended;
	}
	public void setExtended(Class extended) {
		this.extended = extended;
	}
	public List<Field> getProperties() {
		return properties;
	}
	public void setProperties(List<Field> properties) {
		this.properties = properties;
	}
	public List<Constructor> getConstructors() {
		return constructors;
	}
	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public List<Class> getImplemented() {
		return implemented;
	}
	public void setImplemented(List<Class> implemented) {
		this.implemented = implemented;
	}

	@Override
	public String toString() {
		return "ClassModel [name=" + name + ", type=" + type + ", extended=" + extended + ", classPackage="
				+ classPackage + ", properties=" + properties + ", constructors=" + constructors + ", methods="
				+ methods + ", implemented=" + implemented + "]";
	}

	
	
}
