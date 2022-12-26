package demo.mql.java.models;

import java.util.List;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;


public class InterfaceModel extends EModel{
	
	private List<InterfaceModel> extentedInterface;
	private List<MethodModel> methods;
	
	
	public InterfaceModel(int id, List<InterfaceModel> extentedInterface) {
		super(id);
		this.extentedInterface = extentedInterface;
	}
	public InterfaceModel(long id, String name, String simpleName, Visibility visibility,
			EModificator modificator) {
		super(id, name, simpleName, visibility, modificator);
	}
	
	public InterfaceModel(long id, String name, String simpleName, Visibility visibility,
			EModificator modificator,
			List<InterfaceModel> extentedInterface , List<MethodModel> methods) {
		super(id, name, simpleName, visibility, modificator);
		this.extentedInterface = extentedInterface;
		this.methods = methods;
	}
	public InterfaceModel(long id, String name, String simpleName, Visibility visibility,
			EModificator modificator,
			 List<MethodModel> methods) {
		super(id, name, simpleName, visibility, modificator);
		this.methods = methods;
	}


	public InterfaceModel(int id, List<InterfaceModel> extentedInterface , List<MethodModel> methods) {
		super(id);
		this.extentedInterface = extentedInterface;
		this.methods = methods;
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
