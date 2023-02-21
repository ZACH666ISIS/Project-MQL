package org.mql.java.example;

import java.io.File;

import org.mql.java.models.ProjectModel;
import org.mql.java.parser.ProjectParser;
import org.mql.java.parser.RelationsParser;
import org.mql.java.ui.MainFrame;
import org.mql.java.ui.loader.XMLLoader;
import org.mql.java.xml.ObjectPersister;
import org.mql.java.xml.XMIPersister;


/**
 * @author Zach
 *
 */
public class Example {

	
	public Example(){
		exp03();
	}
	

	
	void exp01() {
		ProjectParser p = new ProjectParser("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin");
		p.parse();
		RelationsParser rp = new RelationsParser();
		rp.setPackages(p.getPackages());
		rp.etablishRelations();
		ProjectModel model = new ProjectModel();
		model.setAssociation(rp.getAssociations());
		model.setPackages(p.getPackages());
		ObjectPersister persister = new ObjectPersister(new File("resources/document.xml"));
		persister.setObject(model);
		persister.save();

	}
	
	
	void exp03() {
		new MainFrame();
	}
	
	void exp04() {
		XMIPersister p = new XMIPersister("C:\\Users\\Zach\\Projects_JAVA\\UML-Generator\\bin","resources/XMIDoc.xml");	
	}
	
	public static void main(String[] args) {
		new Example();
	}
}
