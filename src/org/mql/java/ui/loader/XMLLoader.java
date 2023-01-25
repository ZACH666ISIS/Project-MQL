package org.mql.java.ui.loader;

import java.util.List;
import java.util.Vector;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.Propertie;
import org.mql.java.ui.models.Relation;
import org.mql.java.ui.models.PackageData;

public class XMLLoader {

	private List<Relation> relations;
	private List<PackageData> packages;
	private DataFormater formater;


	public XMLLoader(String source) {
		packages  = new Vector<>();
		relations = new Vector<>();
		XMLNode root = new XMLNode(source);
		XMLNode pkgs[] = root.child("packages").children();
		XMLNode relations[] = root.child("associations").children();
		packageData(pkgs);
		associations(relations);
		
	}
	
	private void associations(XMLNode[] relations) {
		for(XMLNode relation : relations) {
			this.relations.add(getRelation(relation));
		}
	}
	
	
	private void packageData(XMLNode[] pkgs) {
		for(XMLNode pkg : pkgs) {
			PackageData data = new PackageData(pkg.longAttribute("id"));
			data.setClasses(classData(pkg.child("classes").children()));
			data.setPackageName(pkg.attribute("name"));
			packages.add(data);
			if(pkg.child("packages") != null) {
				packageData(pkg.child("packages").children());
			}
		}
	}

	
	private List<ClassData> classData(XMLNode[] classes){
		List<ClassData> cls = new Vector<>();
		for(XMLNode c : classes) {
			ClassData myc = new ClassData(c.longAttribute("id"));
			myc.setClassName(c.attribute("simpleName"));
			if(c.child("attributes") != null) {
				myc.setFields(fieldData(c.child("attributes").children()));
			}
			if(c.child("methods") != null) {
			myc.setMethods(operationData(c.child("methods").children()));
			}
			cls.add(myc);
		}
		return cls;
	}
	
	private List<Propertie> fieldData(XMLNode[] fields){
		List<Propertie> data = new Vector<>();
		for(XMLNode field : fields) {
			formater = new FieldFormater();
			Propertie e = new Propertie();
			e.isStatic = formater.isStatic(field);
			e.value = formater.data(field);
			data.add(e);
		}
		return data;
	}
	
	private List<Propertie> operationData(XMLNode[] methods){
		List<Propertie> data = new Vector<>();
		for(XMLNode m : methods) {
			formater = new MethodFormater();
			Propertie e = new Propertie();
			e.isStatic = formater.isStatic(m);
			e.value = formater.data(m);
			data.add(e);
		}
		return data;
	}
	
	
	private Relation getRelation(XMLNode relation) {
		Relation rel =  new Relation();
		rel.setId1(relation.longAttribute("parent"));
		rel.setId2(relation.longAttribute("son"));
		return rel;
	}

	
	public List<PackageData> getPackages() {
		return packages;
	}
	
	public List<Relation> getRelations(){
		return relations;
	}

	public void setPackages(List<PackageData> packages) {
		this.packages = packages;
	}
}
