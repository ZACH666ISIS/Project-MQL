package org.mql.java.parser;

import java.util.List;
import java.util.UUID;
import java.util.Vector;
import org.mql.java.models.ClassModel;
import org.mql.java.models.ClassType;

@SuppressWarnings("rawtypes")
public class ClassParser {

	private List<ClassModel> classes;
	private List<Class> cls;

	
	public ClassParser(String path) {
		cls = new ClassExplorer(path).getFoundedClasses();
		classes = new Vector<>();
	}

	public boolean parse() {
		if(cls==null) 
			return false;
		for(Class c : cls) {
			
			
			classes.add(
					new ClassModel(
							c.getCanonicalName().hashCode(),
							c.getCanonicalName(),
							c.getSimpleName(),
							getType(c),
							getExtends(c),
							c.getPackage(),
							List.of(c.getDeclaredFields()),
							List.of(c.getDeclaredConstructors()),
							List.of(c.getDeclaredMethods()),
							List.of(c.getInterfaces())						
						)			
			);
		}	
		return true;
	}
	
	
	private Class getExtends(Class c) {
		if(c.getSuperclass() == null) {
			return null;
		}
		if(c.getSuperclass().getCanonicalName().equals("java.lang.Object")) {
			return null;
		}	
		return c.getSuperclass();		
	}

	private ClassType getType(Class c) {
		if(c.isInterface())
			return ClassType.INTERFACE;
		else if(c.isEnum())
			return ClassType.ENUM;
		else if(c.isPrimitive())
			return ClassType.DATATYPE;
		else
			return ClassType.DEFAULT;
	}
	
	public List<ClassModel> getClasses() {
		return classes;
	}
}