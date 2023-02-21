package org.mql.java.parser;


import java.util.List;
import java.util.Vector;

import org.mql.java.enums.Cardinal;
import org.mql.java.models.Association;
import org.mql.java.models.Attribute;
import org.mql.java.models.ClassModel;
import org.mql.java.models.EnumModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.assotiation.AssociationType;
import org.mql.java.models.assotiation.Values;


public class RelationsParser {

	/**
	 * Relation can be :
	 * 					--> Generalisation
	 * 					--> Realization
	 * 					--> Association
	 * 					--> Dependencies (can't be represented !!!)
	 */
	
	private List<PackageModel> packages;
	private List<Association> associations;
	
	public RelationsParser() {
		associations = new Vector<>();
	}
	
	public void setPackages(List<PackageModel> packages) {
		this.packages = packages;
	}
	
	
	public void etablishRelations() {
		etablishRelations(packages);
	}
	
	private void etablishRelations(List<PackageModel> packages) {
		if(packages != null) {
			for(PackageModel p : packages) {
				searchAndEtablish(p);
				if(p.getPackages() != null) {
					etablishRelations(p.getPackages());
				}
			}
		}
	}
	
	private void searchAndEtablish(PackageModel p) {
		for(EnumModel e : p.getEnumes()) {
			if(e.getImplementedEnums() != null)	
				associations.addAll(getRealization(e));
		}
		for(InterfaceModel in : p.getInterfaces()) {
			if(in.getExtentedInterface() != null)
				associations.addAll(getGeneralisation(in));
		}
		for(ClassModel c : p.getClasses()) {
			if(c.getImplemented() != null)
				associations.addAll(getRealization(c));
			if(c.getExtended() != null)
				associations.add(getGeneralisation(c));
			parcoursClasses(c,packages);
		}
	}
	
	private List<Association> getRealization(ClassModel c) {
		List<Association> as = new Vector<>();
		for(InterfaceModel in : c.getImplemented()) {
			Association a = new Association();
			a.setType(AssociationType.IMPLEMENTATION);
			a.setSon(c.getId());
			a.setParent(in.getId());
			as.add(a);
		}
		return as;
	}
	
	private List<Association> getRealization(EnumModel e) {
		List<Association> as = new Vector<>();
		for(InterfaceModel in : e.getImplementedEnums()) {
			Association a = new Association();
			a.setType(AssociationType.IMPLEMENTATION);
			a.setSon(e.getId());
			a.setParent(in.getId());
			as.add(a);
		}
		return as;
	}
	
	private List<Association> getGeneralisation(InterfaceModel i) {
		List<Association> as = new Vector<>();
		for(InterfaceModel in : i.getExtentedInterface()) {
			Association a = new Association();
			a.setType(AssociationType.INHERITANCE);
			a.setSon(i.getId());
			a.setParent(in.getId());
			as.add(a);
		}
		return as;
	}
	
	private Association getGeneralisation(ClassModel c) {
		Association a = new Association();
		a.setType(AssociationType.INHERITANCE);
		a.setSon(c.getId());
		a.setParent(c.getExtended().getId());
		return a;
	}
	
	private void parcoursClasses(ClassModel c, List<PackageModel> pkgs) {
		for(PackageModel p : pkgs) {
			for(ClassModel cls : p.getClasses()) {
				if(c.getId() != cls.getId())
					setAssociation(c, cls);
			}
			if(p.getPackages() != null) {
				parcoursClasses(c, p.getPackages());
			}
		}
	}
	
	private void setAssociation(ClassModel c1,ClassModel c2) {
		Cardinal son = searchAssociation(c1,c2),
				 parent = searchAssociation(c2, c1);
		long id = Converter.idGenerator(c1.hashCode() + c2.hashCode());
		if(associationExists(id))
			return;
		if(son != Cardinal.NONE || parent != Cardinal.NONE) {
			Association a = new Association();
			a.setId(id);
			a.setSon(c1.getId());
			a.setParent(c2.getId());
			Values pv = new Values();
			pv.setLowerValue(Cardinal.NONE);
			pv.setUpperValue(son);
			a.setSonValue(pv);
			pv = new Values();
			pv.setUpperValue(parent);
			pv.setLowerValue(Cardinal.NONE);
			a.setParentValue(pv);
			a.setType(AssociationType.ASSOCIATION);
			associations.add(a);
		}
		
	}
	
	private boolean associationExists(long id) {
		for(Association a : associations) {
			if(a.getId() == id)
				return true;
		}
		return false;
	}
	
	private  Cardinal searchAssociation(ClassModel c1, ClassModel c2) {
	
		for(Attribute a : c1.getAttributes()) {
			if(isReference(a, c2)) {
				if(a.isList())
					return Cardinal.UNBOUNDED;
				return Cardinal.ONCE;
			}
		}
		return Cardinal.NONE;
		
	}
	
	private boolean isReference(Attribute a, ClassModel c) {

		return (a.getIdRef() == c.getId());
	}
	
	
	public List<Association> getAssociations() {
		return associations;
	}
	
	

}
