package org.mql.java.example;

import java.io.File;
import java.util.List;


import org.mql.java.models.Relation;
import org.mql.java.parser.ClassParser;
import org.mql.java.parser.RelationParser;
import org.mql.java.ui.MainFrame;
import org.mql.java.xml.XMIPersister;

import demo.mql.java.parser.ProjectParser;
import demo.mql.java.xml.ObjectPersister;


/**
 * @author Zach
 *
 */
public class Example {

	
	public Example(){
		exp02();
	}
	
	void exp01() {
		ClassParser c = new ClassParser("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin");
		c.parse();
		List<Relation> l = new RelationParser(c.getClasses()).getRelations();
		new XMIPersister(c.getClasses(), l);

	}
	
	void exp02() {
		ProjectParser p = new ProjectParser("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin");
		p.parse();
		ObjectPersister persister = new ObjectPersister(new File("resources/document.xml"));
		persister.addObjects(p.getPackages());
		persister.save();

	}
	
	void exp03() {
		new MainFrame();
	}
	
	public static void main(String[] args) {
		new Example();
	}
}
