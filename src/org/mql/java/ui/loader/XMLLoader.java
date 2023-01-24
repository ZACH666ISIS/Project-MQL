package org.mql.java.ui.loader;

import java.util.List;
import java.util.Vector;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PackageData;

public class XMLLoader {

	List<PackageData> packages;
	public XMLLoader(String source) {
		packages  = new Vector<>();
		XMLNode root = new XMLNode(source);
		XMLNode pkgs[] = root.child("packages").children();
		packageData(pkgs);
		
		packages.stream().forEach((e)->{
			System.out.println(e.getPackageName());
			System.err.println("-------------------");
			e.getClasses().forEach((c)-> {
				System.out.println(c.getClassName());
				System.err.println(c.getFields());
				System.out.println(c.getMethods());
			});
			System.err.println("-------------------");
		});
		
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
			StringBuffer s = new StringBuffer(field.child("visibility").value()+" ");
			s.append(field.attribute("name")+" : "+field.attribute("type"));
			boolean b = Boolean.valueOf(field.attribute("isList"));
			if(b) {
				s.append("[]");
			}
			data.add(s.toString());
		}
		return data;
	}
	
	private List<String> operationData(XMLNode[] methods){
		List<String> data = new Vector<>();
		for(XMLNode m : methods) {
			StringBuffer s = new StringBuffer(m.child("visibility").value()+" ");
			String params = "";
			if(m.child("parameters") != null) {
				params = parameterData(m.child("parameters").children());
			}
			s.append(m.attribute("name")+" ( "+params+") : "+m.attribute("returnType"));
			data.add(s.toString());
		}
		return data;
	}
	
	private String parameterData(XMLNode[] parameters) {
		StringBuffer parameterz = new StringBuffer(" ");
		for(XMLNode p : parameters) {
			parameterz.append(p.value()+"; ");
		}
		return parameterz.toString();
	}
}
