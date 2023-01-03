package org.mql.java.xml;


import demo.mql.java.models.EnumModel;
import demo.mql.java.models.MethodModel;
import demo.mql.java.models.PackageModel;
import demo.mql.java.models.ClassModel;
import demo.mql.java.parser.ProjectParser;
public class Test {

	public Test() {
		exp1();
	}

	void exp1(){
		ProjectParser c = new ProjectParser("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin");
		c.parse();
		for(PackageModel p : c.getPackages()) {
			System.out.println("-->"+p.getId() +"  : name = "+  p.getName()+"  [");
			for(ClassModel e : p.getClasses()) {
				System.out.println("xsi:id = "+e.getId()+" visibility = "+e.getVisibility()+"  name = "+e.getSimpleName()+" Extends = "+e.getExtended());
				
				for(MethodModel m : e.getMethods()) {
					
					for(String s : m.getParameters()) {
						System.out.println(s);
					}
					
				}
				
				}


			
			}
			System.out.println("]");
		}
		
	
	
	public static void main(String[] args) {
		new Test();
	}
	
}

