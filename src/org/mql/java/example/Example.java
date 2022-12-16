package org.mql.java.example;


import java.util.List;
import org.mql.java.models.Relation;
import org.mql.java.parser.ClassParser;
import org.mql.java.parser.RelationParser;
import org.mql.java.xml.DOMPersister;

/**
 * @author Zach
 *
 */
public class Example {

	
	public Example(){
		exp01();
	}
	
	void exp01() {
		ClassParser c = new ClassParser("C:\\Users\\Zach\\eclipse-workspace\\ProblemeSolving\\bin");
		c.parse();
		List<Relation> l = new RelationParser(c.getClasses()).getRelations();
		new DOMPersister(c.getClasses(), l);

	}
	
	public static void main(String[] args) {
		new Example();
	}
}
