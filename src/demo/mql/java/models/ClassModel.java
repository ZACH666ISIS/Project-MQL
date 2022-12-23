package demo.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import demo.mql.java.enums.ClassType;

@SuppressWarnings("rawtypes")
public class ClassModel {

	private String name;
	private ClassType type;
	private Class extended;
	private Package classPackage;
	private List<Attribute> attribute;
	private List<Constructor> constructors;
	private List<MethodModel> methods;
	private List<InterfaceModel> implemented;
	


	
	
}
