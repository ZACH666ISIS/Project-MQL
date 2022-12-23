package org.mql.java.example;


import java.lang.reflect.Field;
import java.util.List;

import org.mql.java.models.ClassModel;
import org.mql.java.models.Relation;
import org.mql.java.parser.ClassExplorer;
import org.mql.java.parser.ClassParser;
import org.mql.java.parser.RelationParser;
import org.mql.java.xml.DOMPersister;
import org.mql.java.xml.XMIPersister;

/**
 * @author Zach
 *
 */
public class Example {

	
	public Example(){
		exp01();
	}
	
	void exp01() {
		ClassParser c = new ClassParser("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin");
		c.parse();
		List<Relation> l = new RelationParser(c.getClasses()).getRelations();
		new XMIPersister(c.getClasses(), l);

	}
	
	void exp03() {

	}
	
	public static void main(String[] args) {
		new Example();
	}
}
