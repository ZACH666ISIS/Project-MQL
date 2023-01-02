package demo.mql.java.xml;



import demo.mql.java.models.PackageModel;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.List;

import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import demo.mql.java.models.Attribute;
import demo.mql.java.models.ClassModel;

public class XMIPersister {
	
	private Document document;
	Element root;
	
	public XMIPersister(List<?> o) {
		this("UML_Diagram",o);

	}
	public XMIPersister(Object o) {
		this("UML_Diagram",o);

	}
	public XMIPersister(String project,List<?> o) {
		init(project);
		root.appendChild(createObject(o));
		createFile();
	}
	
	public XMIPersister(String project,Object o) {
		init(project);
		root.appendChild(createObject(o));
		createFile();
	}
	private void init(String project) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder =  factory.newDocumentBuilder();
			document =builder.newDocument();
			root = document.createElement("uml:Model");
		} catch (Exception e) {}
	
	}
	

	

	

	public void createFile() {
		try {
			root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xmi","http://www.omg.org/XMI");
			root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:uml","http://www.eclipse.org/uml2/1.0.0/UML");
			document.appendChild(root);
			Result res = new StreamResult(new File("resources/XMIdiagramDEMO.xml"));
			DOMSource source = new DOMSource(document);
			TransformerFactory trsFormFactory = TransformerFactory.newInstance();
			Transformer transformer = trsFormFactory.newTransformer();
			transformer.transform(source, res);
		} catch (Exception e) {}
		
	}

	
	public Element createObject(Object o) {
			Element e = document.createElement(o.getClass().getSimpleName());
			for(Field f : o.getClass().getDeclaredFields()) {
				
				if(Modifier.isPrivate(f.getModifiers())) {
					f.setAccessible(true);
				}
				if(f.getType().isPrimitive() || isNative(f)) {
				e.setAttribute(f.getName(), getValue(f, o).toString());
				}
				else if(Collection.class.isAssignableFrom(f.getType()) && !isNative(f)){	
					e.appendChild(createObject((List<?>) getValue(f,o)));		
				}
				
			}
			return e;
		
			
	}

	
	public Element createObject(List<?> objects) {
			Element e = document.createElement("OwnedMember");
			for(Object o : objects) {
				e.appendChild(
				createObject(o)
				);
			}
			return e;
		
			
	}
	
	private static boolean isNative(Field f) {
		String typeName =f.getGenericType().getTypeName();
		if(typeName.contains("java.lang.String")) return true;
		return false;
	}
	
	private static Object getValue(Field f, Object o) {
		try {
			return f.get(o);
			
		} catch (Exception e) {	}
		return null;
	}
	
	public String attribute(Node node,String name) {
		NamedNodeMap atts =node.getAttributes();
		return atts.getNamedItem(name).getNodeValue();
	}
	

}
