package org.mql.java.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.models.Cardinal;
import org.mql.java.models.ClassModel;
import org.mql.java.models.Relation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DOMPersister {
	
	private Document document;
	Element root;
	public DOMPersister(List<ClassModel> classes, List<Relation> relations) {
		init();
		fillPackages(classes);
		fillRelations(relations);
		createFile();
	}
	
	private void init() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder =  factory.newDocumentBuilder();
			document =builder.newDocument();
			root = document.createElement("diagram");
			
		} catch (Exception e) {}
	
	}
	
	private void fillPackages(List<ClassModel> classes) {
		for(ClassModel c : classes) {
			createClass(c);
		}
	}
	
	private void fillRelations(List<Relation> relations) {
		Element rel = document.createElement("relations");
		for(Relation r : relations) {
			Element relation = document.createElement("relation");
			relation.setAttribute("type", r.getType().name());
			relation.appendChild(createClassRelation(r.getFirst(),r.getFirstCard()));
			relation.appendChild(createClassRelation(r.getSecond(),r.getSecondCard()));
			rel.appendChild(relation);
		}
		
		root.appendChild(rel);
	}
	
	private Element createClassRelation(ClassModel c , Cardinal card) {
		Element cls = document.createElement("class");
		cls.setAttribute("name", c.getName());
		cls.setAttribute("card", card.name());
		return cls;
	}
	public void createFile() {
		try {
			document.appendChild(root);
			Result res = new StreamResult(new File("resources/diagram.xml"));
			DOMSource source = new DOMSource(document);
			TransformerFactory trsFormFactory = TransformerFactory.newInstance();
			Transformer transformer = trsFormFactory.newTransformer();
			transformer.transform(source, res);
		} catch (Exception e) {}
		
	}
	
	public void createClass(ClassModel c) {
		Element pkg = createPackage(c.getClassPackage().getName());
		Element cls = document.createElement("class");
		cls.setAttribute("name", c.getName());
		cls.setAttribute("type", c.getType().name());
		pkg.appendChild(cls);

	}
	
	public Element createPackage(String name) {
		if(searchPackage(name) == null) {
			Element pkg = document.createElement("package");
			pkg.setAttribute("name", name);
			root.appendChild(pkg);
			return pkg;
		}
		return searchPackage(name);
	}
	
	public Element searchPackage(String name) {
		NodeList list = root.getChildNodes();
		for(int i=0;i<list.getLength();i++) {
			if(list.item(i).getNodeName().equals("package"))
				if(attribute(list.item(i),"name").equals(name))
					return (Element) list.item(i);
					
		}
		return null;
	}
	
	public String attribute(Node node,String name) {
		NamedNodeMap atts =node.getAttributes();
		return atts.getNamedItem(name).getNodeValue();
	}
	

	
	



}
