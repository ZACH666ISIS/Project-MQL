package org.mql.java.example;

import org.mql.java.models.ClassModel;
import org.mql.java.parser.ClassParser;
import org.mql.java.parser.RelationParser;

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
		new RelationParser(c.getClasses());

	}
	
	public static void main(String[] args) {
		new Example();
	}
}
