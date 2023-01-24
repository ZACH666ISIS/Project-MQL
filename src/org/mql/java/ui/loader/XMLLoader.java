package org.mql.java.ui.loader;

import java.util.List;
import java.util.Vector;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PackageData;

public class XMLLoader {

	private List<PackageData> packages;
	private DataFormater formater;


	public XMLLoader(String source) {
		packages  = new Vector<>();
		XMLNode root = new XMLNode(source);
		XMLNode pkgs[] = root.child("packages").children();
		packageData(pkgs);
		
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
	
	private List<String> fieldData(XMLNode[] fields){
		List<String> data = new Vector<>();
		for(XMLNode field : fields) {
			formater = new FieldFormater();
			String s = formater.data(field);
			data.add(s);
		}
		return data;
	}
	
	private List<String> operationData(XMLNode[] methods){
		List<String> data = new Vector<>();
		for(XMLNode m : methods) {
			formater = new MethodFormater();
			String s = formater.data(m);
			data.add(s);
		}
		return data;
	}
	

	
	public List<PackageData> getPackages() {
		return packages;
	}


	public void setPackages(List<PackageData> packages) {
		this.packages = packages;
	}
}
