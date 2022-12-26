package org.mql.java.xml;

import java.io.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.mql.java.parser.ClassParser;

import demo.mql.java.models.EModel;
import demo.mql.java.models.PackageModel;
import demo.mql.java.parser.ProjectParser;
public class Test {

	public Test() {
		exp1();
	}

	void exp1(){
		ProjectParser c = new ProjectParser("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin");
		c.parse();
		for(PackageModel p : c.getPackages()) {
			System.out.println("-->"+p.getId() +"  : name = "+  p.getName()+"  {");
			for(EModel e : p.getClasses()) {
				System.out.println(e.getId() +"   : name = "+e.getSimpleName() +" visibility = "+e.getVisibility());
			}
			System.out.println("}");
		}
		;
	
	}
	
	
	public static void main(String[] args) {
		new Test();
	}
	
}

