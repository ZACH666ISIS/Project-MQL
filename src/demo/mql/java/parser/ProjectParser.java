package demo.mql.java.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.Vector;


import org.mql.java.parser.ClassExplorer;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;
import demo.mql.java.models.Attribute;
import demo.mql.java.models.ClassModel;
import demo.mql.java.models.ConstructorModel;
import demo.mql.java.models.EModel;
import demo.mql.java.models.EnumModel;
import demo.mql.java.models.InterfaceModel;
import demo.mql.java.models.MethodModel;
import demo.mql.java.models.PackageModel;

public class ProjectParser {
	
	private List<PackageModel> packageModels;
	

	private List<Class> clsF;
	
	public ProjectParser(String path) {
		clsF = new ClassExplorer(path).getFoundedClasses();
		packageModels = new Vector<>();

	}
	
	
	public boolean parse() {
		if(clsF==null) 
			return false;
		for(Class c : clsF) {
			
			setClass(c);

		}	
		return true;
	}
	
	private void setClass(Class c){
		
			if(packageIndex(c.getPackage()) == -1) {
				packageModels.add(newPackage(c.getPackage()));
			}
			int index = packageIndex(c.getPackage());
			packageModels.get(index).addClass(setEClass(c));		
			
	}
	
	private int packageIndex(Package p) {
		long id = idGenerator(p.getName());
		for(int i=0;i<packageModels.size();i++) {
			
			if(packageModels.get(i).getId() == id)
				return i;
		}
		
		return -1;
		
	}
	
	private EModel setEClass(Class c) {
		if(c.isEnum()) {
			return new EnumModel( idGenerator(c.getCanonicalName()),
					 c.getCanonicalName(),
					 c.getSimpleName(),
					 getVisibility(c.getModifiers()) , 
					 getModificator(c.getModifiers()),
					 getInterfaces(c)
					 );
		}
		else if(c.isInterface()) {
		
				return new InterfaceModel(
						idGenerator(c.getCanonicalName()),
						c.getCanonicalName(),
						c.getSimpleName(),
						getVisibility(c.getModifiers()),
						getModificator(c.getModifiers()),
						getInterfaces(c),
						getMethods(c.getMethods())
						);	
			
			
		}
		else {
			return new ClassModel(
					idGenerator(c.getCanonicalName()),
					c.getCanonicalName(),
					c.getSimpleName(),
					getVisibility(c.getModifiers()) , 
					getModificator(c.getModifiers()),
					getFields(c.getFields()),
					getConstuctors(c.getConstructors()),
					getMethods(c.getMethods()),
					getInterfaces(c)
					);
			
		}
	}
	
	private List<InterfaceModel> getInterfaces(Class c){
		List<InterfaceModel> interfaces = new Vector<InterfaceModel>();
		for(Class intrF  : c.getInterfaces()) {
			interfaces.add(
					new InterfaceModel(
					idGenerator(intrF.getCanonicalName()),
					intrF.getCanonicalName(),
					intrF.getSimpleName(),
					getVisibility(intrF.getModifiers()),
					getModificator(intrF.getModifiers())	
					));		
		}
		
		return interfaces;
	}
	
	
	private PackageModel newPackage(Package p) {
		
		return new PackageModel(idGenerator(p.getName()), p.getName());
	}
	
	private List<Attribute> getFields(Field[] fields){
		
		List<Attribute> EAttributes = new Vector<Attribute>();
		for(Field f : fields) {
			
			EAttributes.add(new Attribute(
					idGenerator(f),
					f.getName(),
					f.getGenericType().getTypeName(),
					getModificator(f.getModifiers()),
					getVisibility(f.getModifiers()),
					isList(f),
					idGenerator(f.getGenericType().getTypeName())

					
					));		
		}
		return EAttributes;
	}
	
	private List<MethodModel> getMethods(Method[] methods){
		List<MethodModel> EMethods = new Vector<MethodModel>();
		
		for(Method m : methods) {
			EMethods.add(new MethodModel(
					idGenerator(m),/*id not important*/
					getVisibility(m.getModifiers()),
					getModificator(m.getModifiers()),
					getParameter(m.getParameters()),
					m.getReturnType().getCanonicalName()
					));
		}		
		return EMethods;
	}
	
	private List<ConstructorModel> getConstuctors(Constructor[] constuctors){
		List<ConstructorModel> ECons = new Vector<ConstructorModel>();
		
		for(Constructor c : constuctors) {
			ECons.add(new ConstructorModel(
					idGenerator(c),/*id not important*/
					getVisibility(c.getModifiers()),
					getModificator(c.getModifiers()),
					getParameter(c.getParameters())
					));
		}		
		return ECons;
	}
	
	private HashMap<String,String> getParameter(Parameter[] parameterz){
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>(); 
		for(Parameter p : parameterz) {
			params.put(p.getName(), p.getType().getCanonicalName());
		}
		return params;
	}
	
	private long idGenerator(Object o) {	
		return o.hashCode()*10;
	}
	
	private Visibility getVisibility(int mod) {
		if(Modifier.isPrivate(mod)) return Visibility.PRIVATE;
		else if(Modifier.isPublic(mod)) return Visibility.PUBLIC;
		else if(Modifier.isProtected(mod)) return Visibility.PROTECTED;
		else return Visibility.DEFAULT;
	}
	
	private EModificator getModificator(int mod) {
		if(Modifier.isFinal(mod)) return EModificator.FINAL;
		else if(Modifier.isStatic(mod)) return EModificator.STATIC;
		else return null;
	}
	
	public boolean isList(Field f) {
		String type = f.getGenericType().getTypeName();	
		if((type.contains("<") && type.contains(">")) || (type.toLowerCase().contains("list"))) {
			return true;
		}
		else if(type.contains("[]")) {
			return true;
		}
		return false;
	}
	
	private Class getExtends(Class c) {
		if(c.getSuperclass() == null) {
			return null;
		}
		if(c.getSuperclass().getSuperclass().getCanonicalName().equals("java.lang.Object")) {
			return c;
		}	
		return c.getSuperclass();		
	}

	public List<PackageModel> getPackages() {
		return packageModels;
	}



}
