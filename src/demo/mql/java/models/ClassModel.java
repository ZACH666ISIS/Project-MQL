package demo.mql.java.models;


import java.util.List;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;


public class ClassModel extends EModel{
	
	private List<Attribute> attribute;
	private List<ConstructorModel> constructors;
	private List<MethodModel> methods;
	private List<InterfaceModel> implemented;
	
	
	public ClassModel(long id) {
		super(id);
	}


	
	public ClassModel(long id, String name, String simpleName, Visibility visibility,
			EModificator modificator) {
		super(id, name, simpleName, visibility, modificator);
	}



	public ClassModel(long id, List<Attribute> attribute, List<ConstructorModel> constructors, List<MethodModel> methods,
			List<InterfaceModel> implemented) {
		super(id);
		this.attribute = attribute;
		this.constructors = constructors;
		this.methods = methods;
		this.implemented = implemented;
	}

	public ClassModel(  long id, 
						String name, 
						String simpleName,
						Visibility visibility,
						EModificator modificator,
						List<Attribute> attribute,
						List<ConstructorModel> constructors,
						List<MethodModel> methods,
						List<InterfaceModel> implemented) {
		super(id, name, simpleName, visibility, modificator);
		this.attribute = attribute;
		this.constructors = constructors;
		this.methods = methods;
		this.implemented = implemented;
	}

	public List<Attribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
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
	


	
	
}
