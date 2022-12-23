package org.mql.java.parser;

import java.lang.reflect.Field;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Vector;
import org.mql.java.models.Cardinal;
import org.mql.java.models.ClassModel;
import org.mql.java.models.ClassType;
import org.mql.java.models.Relation;
import org.mql.java.models.RelationType;

@SuppressWarnings("rawtypes")

public class RelationParser {

	private List<ClassModel> classes;
	private List<Relation> relations;
	
	public RelationParser() {
		
	}
	
	public RelationParser(List<ClassModel> classes) {
		this.classes = classes;
		relations = new Vector<>();
		wired();
	}
	
	
	private boolean isInHeritance(ClassModel c1, ClassModel c2) {
		if(c2.getExtended() == null)
			return false;
		if(c1.getName().equals(c2.getExtended().getCanonicalName())){
			return true;
		}
		
		return false;
	}
	Cardinal searchAssociation(ClassModel c1, ClassModel c2) {
		Cardinal cardinal;
		String type;
		for(Field f : c1.getProperties()) {
			SimpleEntry<Cardinal, String> field = fieldType(f);
			type = field.getValue();
			cardinal = field.getKey();
			if(isAssociation(type, c2)) {
				return cardinal;
			}
		}

		return Cardinal.NONE;
	}
	
	boolean isAssociation(String type, ClassModel c) {
		if(type.equals(c.getName())) {
			return true;
		}
		return false;
	}
	
	private void  setAssociationsRel(ClassModel c1,ClassModel c2) {
		Cardinal cardinal = searchAssociation(c1,c2),
				 secCardinal = searchAssociation(c2,c1);
		
		if(cardinal != Cardinal.NONE && secCardinal != Cardinal.NONE) {
			relations.add(
					new Relation(c1, c2, cardinal , secCardinal , RelationType.ASSOCIATION)
					);
		}
			
	}
	
	public List<ClassModel> getClasses() {
		return classes;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	SimpleEntry<Cardinal, String> fieldType(Field f) {
		String type = f.getGenericType().getTypeName();	
		if(type.contains("<") && type.contains(">")) {
			type= type.substring(type.indexOf("<")+1, type.lastIndexOf(">"));
			return new SimpleEntry<Cardinal, String>(Cardinal.UNBOUNDED , type);
		}
		else if(type.contains("[]")) {
			type = type.replace("[]", "");
			return new SimpleEntry<Cardinal, String>(Cardinal.UNBOUNDED, type);
		}
		return new SimpleEntry<Cardinal, String>(Cardinal.ONCE, type);
	}
	
	
	
	boolean isImplementation(ClassModel c1,ClassModel c2) {
		if(c2.getType() != ClassType.INTERFACE) {
			return false;
		}
		for(Class c : c1.getImplemented()) {
			if(c.getCanonicalName().equals(c2.getName())) {
				return true;
			}
		}
		return false;
	}
	
	
	void setInHeritanceRel(ClassModel c1 ,ClassModel c2) {
		if( isInHeritance(c1,c2) ) {
			relations.add(new Relation(c1,c2,Cardinal.NONE,Cardinal.NONE,RelationType.GENERALIZATION));
		}
		else if ( isInHeritance(c2,c1) ){
			relations.add(new Relation(c2,c1,Cardinal.NONE,Cardinal.NONE,RelationType.GENERALIZATION));
		}
	}
	
	
	void setImplementationsRel(ClassModel c1,ClassModel c2) {
		if( isImplementation(c1,c2)) {
			relations.add(new Relation(c1,c2,Cardinal.NONE,Cardinal.NONE,RelationType.IMPLEMENTATION));
		}
		else if( isImplementation(c2,c1)) {
			relations.add(new Relation(c2,c1,Cardinal.NONE,Cardinal.NONE,RelationType.IMPLEMENTATION));
		}
	}
	
	private void etablishRelations(ClassModel c1, ClassModel c2) {
		setInHeritanceRel(c1,c2);
		setImplementationsRel(c1,c2);
		setAssociationsRel(c1,c2);
	}
	
	
	private void wired() {
		for(int i=0;i<classes.size()-1;i++) {
			for(int j = i+1 ; j<classes.size();j++) {
				etablishRelations(classes.get(i), classes.get(j));
			}
		}
		
	}
	
	
	
	
	
	
}
