package org.mql.java.ui.loader;

import java.util.List;
import java.util.Vector;

import org.mql.java.models.assotiation.AssociationType;
import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.EnumData;
import org.mql.java.ui.models.InterfaceData;
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
			XMLNode classes = pkg.child("classes");
			data.setPackageName(pkg.attribute("name"));
			if(classes != null) {
				data.setClasses(classData(classes.children()));
				packages.add(data);
			}
			classes = pkg.child("enumes");
			if(classes != null) {
				data.setEnumes(enumData(classes.children()));
				packages.add(data);
			}
			classes = pkg.child("interfaces");
			if(classes != null) {
				data.setInterfaces(interFaceData(classes.children()));
				packages.add(data);
			}
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
	private List<EnumData> enumData(XMLNode[] enums){
		List<EnumData> enm = new Vector<>();
		for(XMLNode e : enums) {
			EnumData newEnum = new EnumData();
			newEnum.setName(e.attribute("simpleName"));
			newEnum.setId(e.longAttribute("id"));
			XMLNode elements = e.child("elements");
			if(elements != null) {
				newEnum.setElements(getNamesElements(elements.children()));
			}
			enm.add(newEnum);
		}
		return enm;
	}
	
	private List<InterfaceData> interFaceData(XMLNode[] interfaces){
		List<InterfaceData> itrs = new Vector<>();
		for(XMLNode i : interfaces) {
			InterfaceData newInterface = new InterfaceData();
			newInterface.setName(i.attribute("simpleName"));
			newInterface.setId(i.longAttribute("id"));
			XMLNode methods = i.child("methods");
			if(methods != null) {
				newInterface.setMethods(operationData(methods.children()));
			}
			itrs.add(newInterface);

		}
		return itrs;
	}
	
	private List<String> getNamesElements(XMLNode[] elements){
		List<String> data = new Vector<>();
		for(XMLNode e : elements) {
			data.add(e.value());
		}
		return data;
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
		XMLNode type = relation.child("type");
		if(type != null) {
			rel.setType(AssociationType.valueOf(type.value()));
		}
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
